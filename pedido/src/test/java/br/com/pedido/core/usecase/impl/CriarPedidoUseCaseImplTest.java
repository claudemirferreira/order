package br.com.pedido.core.usecase.impl;

import br.com.pedido.core.domain.ClienteDomain;
import br.com.pedido.core.domain.ItemPedidoDomain;
import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.core.gateways.ClienteGateway;
import br.com.pedido.core.gateways.PedidoGateway;
import br.com.pedido.core.gateways.PedidoMessageGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarPedidoUseCaseImplTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private PedidoMessageGateway pedidoMessageGateway;

    @InjectMocks
    private CriarPedidoUseCaseImpl criarPedidoUseCase;

    private ClienteDomain cliente;
    private List<ItemPedidoDomain> itens;

    @BeforeEach
    void setUp() {
        // Configuração inicial para os testes
        cliente = new ClienteDomain(1L, "João Silva", "joao@example.com", "xx");
        itens = Collections.singletonList(new ItemPedidoDomain(1L, 1L,  1, new BigDecimal( 5000.0)));
    }

    @Test
    void testExecutar_Sucesso() {
        // Arrange
        when(clienteGateway.findById(1L)).thenReturn(cliente);
        when(pedidoGateway.salvar(any(PedidoDomain.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        PedidoDomain pedido = criarPedidoUseCase.executar(1L, itens);

        // Assert
        assertNotNull(pedido);
        assertEquals(cliente, pedido.getCliente());
        assertEquals(itens, pedido.getItens());

        // Verifica se os métodos dos gateways foram chamados
        verify(clienteGateway, times(1)).findById(1L);
        verify(pedidoGateway, times(1)).salvar(any(PedidoDomain.class));
        verify(pedidoMessageGateway, times(1)).send(any(PedidoDomain.class));
    }

    @Test
    void testExecutar_ClienteNaoEncontrado() {
        // Arrange
        when(clienteGateway.findById(1L)).thenReturn(null);

        // Act
        PedidoDomain pedido = criarPedidoUseCase.executar(1L, itens);

        // Assert
        assertNotNull(pedido);
        assertEquals(itens, pedido.getItens());

        // Verifica se os métodos dos gateways foram chamados
        verify(clienteGateway, times(1)).findById(1L);
        verify(pedidoGateway, times(1)).salvar(any(PedidoDomain.class));
        verify(pedidoMessageGateway, times(1)).send(any(PedidoDomain.class));
    }
}