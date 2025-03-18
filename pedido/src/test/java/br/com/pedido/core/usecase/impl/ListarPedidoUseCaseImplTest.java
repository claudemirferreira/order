package br.com.pedido.core.usecase.impl;

import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.core.gateways.PedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ListarPedidoUseCaseImplTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private ListarPedidoUseCaseImpl listarPedidoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldListPedidos() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        PedidoDomain pedido1 = new PedidoDomain();
        pedido1.setId(1L);
        PedidoDomain pedido2 = new PedidoDomain();
        pedido2.setId(2L);

        List<PedidoDomain> pedidos = List.of(pedido1, pedido2);
        Page<PedidoDomain> pagePedidos = new PageImpl<>(pedidos, pageable, pedidos.size());

        when(pedidoGateway.listarPedidos(pageable)).thenReturn(pagePedidos);

        // When
        Page<PedidoDomain> result = listarPedidoUseCase.executar(pageable);

        // Then
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getContent().size());
        assertEquals(pedido1.getId(), result.getContent().get(0).getId());
        assertEquals(pedido2.getId(), result.getContent().get(1).getId());
    }
}
