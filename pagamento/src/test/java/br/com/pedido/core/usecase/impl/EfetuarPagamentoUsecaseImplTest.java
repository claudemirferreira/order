package br.com.pedido.core.usecase.impl;

import br.com.pedido.core.domain.PagamentoDomain;
import br.com.pedido.core.enums.Status;
import br.com.pedido.core.gateways.PagamentoGateway;
import br.com.pedido.core.gateways.PagamentoMessagingGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EfetuarPagamentoUsecaseImplTest {

    @Mock
    private PagamentoGateway pagamentoGateway;

    @Mock
    private PagamentoMessagingGateway pagamentoMessagingGateway;

    @InjectMocks
    private EfetuarPagamentoUsecaseImpl efetuarPagamentoUsecase;

    private PagamentoDomain pagamentoDomain;

    @BeforeEach
    void setUp() {
        pagamentoDomain = new PagamentoDomain();
        pagamentoDomain.setPedidoId(123L);
        pagamentoDomain.setStatusPagamento(Status.PENDENTE);
    }

    @Test
    void testExecute() {
        // Arrange: Mock the behavior of pagamentoGateway and pagamentoMessagingGateway
        when(pagamentoGateway.salvar(any(PagamentoDomain.class))).thenReturn(pagamentoDomain);

        // Act: Call the execute method
        efetuarPagamentoUsecase.execute(pagamentoDomain);

        // Assert: Verify that the status and dataPagamento have been updated
        assert pagamentoDomain.getStatusPagamento() == Status.PAGO;
        assert pagamentoDomain.getDataPagamento() != null;

        // Verify that the pagamentoGateway.salvar method was called once with the updated PagamentoDomain
        verify(pagamentoGateway, times(1)).salvar(pagamentoDomain);

        // Verify that the pagamentoMessagingGateway.send method was called once
        verify(pagamentoMessagingGateway, times(1)).send(pagamentoDomain);
    }
}
