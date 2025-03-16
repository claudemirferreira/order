package br.com.pedido.application.mapper;

import br.com.pedido.application.dto.PedidoDTO;
import br.com.pedido.core.domain.ClienteDomain;
import br.com.pedido.core.domain.ItemPedidoDomain;
import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.infrastructure.persistence.entity.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoMapperTest {

    private PedidoMapper pedidoMapper;

    @Mock
    private ItemPedidoMapper itemPedidoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pedidoMapper = new PedidoMapper(itemPedidoMapper);
    }

    @Test
    void toDTO_DeveConverterPedidoDomainParaPedidoDTO() {
        ClienteDomain cliente = ClienteDomain.builder().id(1L).build();
        List<ItemPedidoDomain> itens = List.of(
                ItemPedidoDomain.builder().produtoId(2L).quantidade(1).precoUnitario(BigDecimal.valueOf(100.0)).build()
        );
        PedidoDomain pedidoDomain = PedidoDomain.builder()
                .id(10L)
                .cliente(cliente)
                .dataPedido(LocalDateTime.now())
                .status("PENDENTE")
                .valorTotal(BigDecimal.valueOf(100.0))
                .itens(itens)
                .build();

        when(itemPedidoMapper.toDTO(itens)).thenReturn(List.of());

        PedidoDTO pedidoDTO = pedidoMapper.toDTO(pedidoDomain);

        assertNotNull(pedidoDTO);
        assertEquals(pedidoDomain.getId(), pedidoDTO.id());
        assertEquals(pedidoDomain.getCliente().getId(), pedidoDTO.clienteId());
        assertEquals(pedidoDomain.getDataPedido(), pedidoDTO.dataPedido());
        assertEquals(pedidoDomain.getStatus(), pedidoDTO.status());
        assertEquals(pedidoDomain.getValorTotal(), pedidoDTO.valorTotal());
        verify(itemPedidoMapper, times(1)).toDTO(itens);
    }

    @Test
    void toEntity_DeveConverterPedidoDomainParaPedidoEntity() {
        ClienteDomain cliente = ClienteDomain.builder().id(1L).build();
        List<ItemPedidoDomain> itens = List.of(
                ItemPedidoDomain.builder().produtoId(2L).quantidade(1).precoUnitario(BigDecimal.valueOf(100.0)).build()
        );
        PedidoDomain pedidoDomain = PedidoDomain.builder()
                .id(10L)
                .cliente(cliente)
                .dataPedido(LocalDateTime.now())
                .status("PENDENTE")
                .valorTotal(BigDecimal.valueOf(100.0))
                .itens(itens)
                .build();

        Pedido pedidoEntity = pedidoMapper.toEntity(pedidoDomain);

        assertNotNull(pedidoEntity);
        assertEquals(pedidoDomain.getId(), pedidoEntity.getId());
        assertEquals(pedidoDomain.getCliente().getId(), pedidoEntity.getClienteId());
        assertEquals(pedidoDomain.getDataPedido(), pedidoEntity.getDataPedido());
        assertEquals(pedidoDomain.getStatus(), pedidoEntity.getStatus());
        assertEquals(pedidoDomain.getValorTotal(), pedidoEntity.getValorTotal());
    }
}
