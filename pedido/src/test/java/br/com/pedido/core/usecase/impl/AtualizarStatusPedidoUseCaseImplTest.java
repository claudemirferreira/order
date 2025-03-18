package br.com.pedido.core.usecase.impl;

import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.core.enums.Status;
import br.com.pedido.core.gateways.PedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AtualizarStatusPedidoUseCaseImplTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private AtualizarStatusPedidoUseCaseImpl atualizarStatusPedidoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdatePedidoStatus() {
        // Given
        Long pedidoId = 1L;
        Status novoStatus = Status.PENDENTE;

        PedidoDomain pedidoDomain = new PedidoDomain();
        pedidoDomain.setId(pedidoId);
        pedidoDomain.setStatus(Status.PENDENTE);

        when(pedidoGateway.findById(pedidoId)).thenReturn(pedidoDomain);

        // When
        atualizarStatusPedidoUseCase.executar(pedidoId, novoStatus);

        // Then
        assertEquals(novoStatus, pedidoDomain.getStatus());
        verify(pedidoGateway, times(1)).findById(pedidoId);
        verify(pedidoGateway, times(1)).salvar(pedidoDomain);
    }
}
