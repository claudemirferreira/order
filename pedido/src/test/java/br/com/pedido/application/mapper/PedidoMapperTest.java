package br.com.pedido.application.mapper;

import br.com.pedido.application.dto.PedidoDTO;
import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.infrastructure.persistence.entity.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class PedidoMapperTest {

    private PedidoMapper pedidoMapper;

    @BeforeEach
    void setUp() {
        pedidoMapper = new PedidoMapper(new ModelMapper());
    }

    @Test
    void shouldConvertPedidoToPedidoDomain() {
        // Given
        Pedido pedido = new Pedido();
        pedido.setId(1L);

        // When
        PedidoDomain pedidoDomain = pedidoMapper.toDomain(pedido);

        // Then
        assertNotNull(pedidoDomain);
        assertEquals(pedido.getId(), pedidoDomain.getId());
    }

    @Test
    void shouldConvertPedidoDomainToPedido() {
        // Given
        PedidoDomain pedidoDomain = new PedidoDomain();
        pedidoDomain.setId(2L);

        // When
        Pedido pedido = pedidoMapper.toEntity(pedidoDomain);

        // Then
        assertNotNull(pedido);
        assertEquals(pedidoDomain.getId(), pedido.getId());
    }

    @Test
    void shouldConvertPedidoDomainToPedidoDTO() {
        // Given
        PedidoDomain pedidoDomain = new PedidoDomain();
        pedidoDomain.setId(3L);

        // When
        PedidoDTO pedidoDTO = pedidoMapper.toDTO(pedidoDomain);

        // Then
        assertNotNull(pedidoDTO);
        assertEquals(pedidoDomain.getId(), pedidoDTO.getId());
    }

    @Test
    void shouldConvertPageOfPedidoDomainToPageOfPedidoDTO() {
        // Given
        PedidoDomain pedidoDomain = new PedidoDomain();
        pedidoDomain.setId(4L);
        Page<PedidoDomain> pedidoDomainPage = new PageImpl<>(Collections.singletonList(pedidoDomain));

        // When
        Page<PedidoDTO> pedidoDTOPage = pedidoMapper.toDTOPage(pedidoDomainPage);

        // Then
        assertNotNull(pedidoDTOPage);
        assertFalse(pedidoDTOPage.isEmpty());
        assertEquals(1, pedidoDTOPage.getTotalElements());
        assertEquals(pedidoDomain.getId(), pedidoDTOPage.getContent().get(0).getId());
    }
}
