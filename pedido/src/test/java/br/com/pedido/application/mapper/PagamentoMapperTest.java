package br.com.pedido.application.mapper;

import br.com.pedido.application.dto.PedidoMessageDTO;
import br.com.pedido.core.domain.ClienteDomain;
import br.com.pedido.core.domain.PedidoDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoMapperTest {

    private PagamentoMapper pagamentoMapper;

    @BeforeEach
    void setUp() {
        pagamentoMapper = new PagamentoMapper();
    }

    @Test
    void shouldConvertPedidoDomainToPedidoMessageDTO() {
        // Given
        ClienteDomain clienteDomain = new ClienteDomain();
        clienteDomain.setId(1L);

        PedidoDomain pedidoDomain = new PedidoDomain();
        pedidoDomain.setId(10L);
        pedidoDomain.setCliente(clienteDomain);
        pedidoDomain.setValorTotal(BigDecimal.valueOf(100.50));
        pedidoDomain.setDataPedido(LocalDateTime.of(2025, 3, 16, 14, 0));

        // When
        PedidoMessageDTO pedidoMessageDTO = pagamentoMapper.toDTO(pedidoDomain);

        // Then
        assertNotNull(pedidoMessageDTO);
        assertEquals(pedidoDomain.getId(), pedidoMessageDTO.getId());
        assertEquals(pedidoDomain.getCliente().getId(), pedidoMessageDTO.getClienteId());
        assertEquals(pedidoDomain.getValorTotal(), pedidoMessageDTO.getValorTotal());
        assertEquals(pedidoDomain.getDataPedido(), pedidoMessageDTO.getDataPedido());
    }
}
