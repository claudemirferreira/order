package br.com.pedido.core.usecase.impl;

import br.com.pedido.core.domain.ClienteDomain;
import br.com.pedido.core.domain.ItemPedidoDomain;
import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.core.gateways.ClienteGateway;
import br.com.pedido.core.gateways.PedidoGateway;
import br.com.pedido.core.gateways.PedidoMessageGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CriarPedidoUseCaseImplTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private PedidoMessageGateway pedidoMessageGateway;

    @InjectMocks
    private CriarPedidoUseCaseImpl criarPedidoUseCase;

    private ClienteDomain clienteDomain;
    private List<ItemPedidoDomain> itens;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Configurando um cliente de teste
        clienteDomain = new ClienteDomain();
        clienteDomain.setId(1L);
        clienteDomain.setNome("Cliente Teste");
        clienteDomain.setEmail("cliente@teste.com");
        clienteDomain.setEndereco("Endereco Teste");

        // Configurando um item de pedido de teste
        ItemPedidoDomain itemPedido = new ItemPedidoDomain();
        itemPedido.setProdutoId(1L);
        itemPedido.setQuantidade(2);
        itemPedido.setPrecoUnitario(BigDecimal.valueOf( 50.0));
        itens = List.of(itemPedido);
    }

    @Test
    void shouldCreatePedido() {
        Long clienteId = 1L;

        when(clienteGateway.findById(clienteId)).thenReturn(clienteDomain);

        PedidoDomain pedidoSalvo = new PedidoDomain(clienteDomain, itens);
        when(pedidoGateway.salvar(any(PedidoDomain.class))).thenReturn(pedidoSalvo);

        doNothing().when(pedidoMessageGateway).send(pedidoSalvo);

        PedidoDomain pedidoCriado = criarPedidoUseCase.executar(clienteId, itens);

        assertNotNull(pedidoCriado);
        assertNotNull(pedidoCriado.getCliente());
        assertNotNull(pedidoCriado.getItens());

        verify(pedidoGateway, times(1)).salvar(any(PedidoDomain.class));

        verify(pedidoMessageGateway, times(1)).send(pedidoCriado);
    }
}
