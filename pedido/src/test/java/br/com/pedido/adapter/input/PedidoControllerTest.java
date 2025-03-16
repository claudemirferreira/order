package br.com.pedido.adapter.input;

import br.com.pedido.application.dto.ItemPedidoDTO;
import br.com.pedido.application.dto.PedidoDTO;
import br.com.pedido.application.mapper.ItemPedidoMapper;
import br.com.pedido.application.mapper.PedidoMapper;
import br.com.pedido.core.domain.ClienteDomain;
import br.com.pedido.core.domain.ItemPedidoDomain;
import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.core.usecase.CriarPedidoUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PedidoController.class)
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CriarPedidoUseCase criarPedidoUseCase;

    @MockitoBean
    private ItemPedidoMapper itemPedidoMapper;

    @MockitoBean
    private PedidoMapper pedidoMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private List<ItemPedidoDTO> itemPedidoDTOList;
    private List<ItemPedidoDomain> itemPedidoDomainList;
    private PedidoDomain pedidoDomain;
    private PedidoDTO pedidoDTO;

    @BeforeEach
    void setUp() {
        // Criando um ItemPedidoDTO simulado
        itemPedidoDTOList = List.of(new ItemPedidoDTO(1L, 2, new BigDecimal("10.00")));

        // Criando um ItemPedidoDomain simulado
        itemPedidoDomainList = List.of(ItemPedidoDomain.builder()
                .produtoId(1L)
                .quantidade(2)
                .precoUnitario(new BigDecimal("10.00"))
                .build());

        // Criando um PedidoDomain simulado
        pedidoDomain = PedidoDomain.builder()
                .id(1L)
                .cliente(new ClienteDomain(1L, "Cliente Teste", "teste@email.com", "aaaa"))
                .dataPedido(LocalDateTime.now())
                .status("NOVO")
                .valorTotal(new BigDecimal("20.00"))
                .itens(itemPedidoDomainList)
                .build();

        // Criando um PedidoDTO simulado
        pedidoDTO = new PedidoDTO(
                1L,
                1L,
                LocalDateTime.now(),
                "NOVO",
                new BigDecimal("20.00"),
                itemPedidoDTOList
        );

        // Mockando a conversão de DTO para Domain e vice-versa
        Mockito.when(itemPedidoMapper.toDomain(itemPedidoDTOList)).thenReturn(itemPedidoDomainList);
        Mockito.when(criarPedidoUseCase.executar(eq(1L), any())).thenReturn(pedidoDomain);
        Mockito.when(pedidoMapper.toDTO(pedidoDomain)).thenReturn(pedidoDTO);
    }

    @Test
    void criarPedido_DeveRetornarPedidoDTO_SeCriacaoForSucesso() throws Exception {
        mockMvc.perform(post("/pedidos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemPedidoDTOList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.clienteId").value(1))
                .andExpect(jsonPath("$.status").value("NOVO"))
                .andExpect(jsonPath("$.valorTotal").value(20.00));
    }
}
