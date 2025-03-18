package br.com.pedido.adapter.input;

import br.com.pedido.application.dto.ItemPedidoDTO;
import br.com.pedido.application.dto.PedidoDTO;
import br.com.pedido.application.mapper.ItemPedidoMapper;
import br.com.pedido.application.mapper.PedidoMapper;
import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.core.enums.Status;
import br.com.pedido.core.usecase.CriarPedidoUseCase;
import br.com.pedido.core.usecase.ListarPedidoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PedidoControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private PedidoController pedidoController;

    @Mock
    private CriarPedidoUseCase criarPedidoUseCase;

    @Mock
    private ListarPedidoUseCase listarPedidoUseCase;

    @Mock
    private ItemPedidoMapper itemPedidoMapper;

    @Mock
    private PedidoMapper pedidoMapper;

    private PedidoDTO pedidoDTO;
    private ItemPedidoDTO itemPedidoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController).build();

        itemPedidoDTO = new ItemPedidoDTO(1L, 2, BigDecimal.valueOf(10.0));
        pedidoDTO = new PedidoDTO(1L, 1L, LocalDateTime.now(), Status.PENDENTE, BigDecimal.valueOf(100.0), List.of(itemPedidoDTO));
    }

    @Test
    void testCriarPedido() throws Exception {
        Long clienteId = 1L;
        PedidoDomain pedidoDomain = new PedidoDomain();

        when(criarPedidoUseCase.executar(eq(clienteId), any())).thenReturn(pedidoDomain);
        when(pedidoMapper.toDTO(pedidoDomain)).thenReturn(pedidoDTO);

        // Realizando a requisição POST
        mockMvc.perform(post("/pedidos/{clienteId}", clienteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"produtoId\":1,\"quantidade\":2,\"precoUnitario\":10.0}]"))
                .andExpect(status().isOk())  // Espera o status 200 OK
                .andExpect(jsonPath("$.id").value(pedidoDTO.getId()))
                .andExpect(jsonPath("$.clienteId").value(pedidoDTO.getClienteId()))
                .andExpect(jsonPath("$.valorTotal").value(pedidoDTO.getValorTotal()));

        verify(criarPedidoUseCase, times(1)).executar(eq(clienteId), any());
        verify(pedidoMapper, times(1)).toDTO(pedidoDomain);
    }

}
