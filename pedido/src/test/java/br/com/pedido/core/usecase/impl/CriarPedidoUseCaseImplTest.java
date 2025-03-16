package br.com.pedido.core.usecase.impl;

import br.com.pedido.core.domain.ClienteDomain;
import br.com.pedido.core.domain.ItemPedidoDomain;
import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.core.gateways.ClienteGateway;
import br.com.pedido.core.gateways.PedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarPedidoUseCaseImplTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private CriarPedidoUseCaseImpl criarPedidoUseCase;

    private Long clienteId;
    private ClienteDomain clienteDomain;
    private List<ItemPedidoDomain> itens;
    private PedidoDomain pedidoEsperado;

    @BeforeEach
    void setUp() {
        // Configurando dados fictícios para os testes
        clienteId = 1L;
        clienteDomain = new ClienteDomain(clienteId, "Cliente Teste", "admin@gmail.com", "endereço");
        itens = List.of(new ItemPedidoDomain(1L, 1L, 2, BigDecimal.valueOf(10.0)));
        pedidoEsperado = new PedidoDomain(clienteDomain, itens);

        // Configurando comportamento dos mocks
        when(clienteGateway.findById(clienteId)).thenReturn(clienteDomain);
        when(pedidoGateway.salvar(any(PedidoDomain.class))).thenReturn(pedidoEsperado);
    }

    @Test
    void executar_DeveCriarEPersistirPedidoComSucesso() {
        PedidoDomain resultado = criarPedidoUseCase.executar(clienteId, itens);

        assertNotNull(resultado);
        assertEquals(clienteId, resultado.getCliente().getId());
        assertEquals(1, resultado.getItens().size());
        assertEquals(1L, resultado.getItens().get(0).getProdutoId());

        verify(clienteGateway, times(1)).findById(clienteId);
        verify(pedidoGateway, times(1)).salvar(any(PedidoDomain.class));
    }

}
