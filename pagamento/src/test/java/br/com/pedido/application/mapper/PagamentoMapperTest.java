package br.com.pedido.application.mapper;

import br.com.pedido.application.dto.PedidoMessageDTO;
import br.com.pedido.core.domain.PagamentoDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoMapperTest {

    private PagamentoMapper pagamentoMapper;

    private PedidoMessageDTO pedidoMessageDTO;

    @BeforeEach
    void setUp() {
        pagamentoMapper = new PagamentoMapper();

        pedidoMessageDTO = PedidoMessageDTO.builder()
                .id(123L)
                .clienteId(456L)
                .valorTotal(BigDecimal.valueOf( 789.0))
                .dataPedido(LocalDateTime.now())
                .build();
    }

    @Test
    void testToDomain() {
        PagamentoDomain pagamentoDomain = pagamentoMapper.toDomain(pedidoMessageDTO);

        assertNotNull(pagamentoDomain);
        assertEquals(pedidoMessageDTO.getId(), pagamentoDomain.getPedidoId());
        assertEquals(pedidoMessageDTO.getClienteId(), pagamentoDomain.getClienteId());
        assertEquals(pedidoMessageDTO.getValorTotal(), pagamentoDomain.getValorTotal());
        assertEquals(pedidoMessageDTO.getDataPedido(), pagamentoDomain.getDataPedido());
    }
}
