package br.com.pedido.core.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoDomainTest {

    private PedidoDomain pedido;
    private List<ItemPedidoDomain> itens;
    private ClienteDomain cliente;

    @BeforeEach
    void setUp() {
        // Inicializa um pedido com itens para os testes
        cliente = ClienteDomain.builder().id(1L).build();
        itens = Arrays.asList(
                new ItemPedidoDomain(1L, 1L, 2, BigDecimal.valueOf(10.0)),
                new ItemPedidoDomain(2L, 1L, 3, BigDecimal.valueOf(20.0))
        );
        pedido = new PedidoDomain(cliente, itens);
    }

    @Test
    void construtorPadrao_DeveInicializarListaDeItens() {
        // Arrange & Act
        PedidoDomain pedido = new PedidoDomain();

        // Assert
        assertNotNull(pedido.getItens());
        assertTrue(pedido.getItens().isEmpty());
    }

    @Test
    void construtorComParametros_DeveInicializarCamposCorretamente() {
        assertEquals(cliente, pedido.getCliente());
        assertEquals("PENDENTE", pedido.getStatus());
        assertNotNull(pedido.getDataPedido());
        assertEquals(BigDecimal.valueOf(80.0), pedido.getValorTotal()); // 2 * 10 + 3 * 20 = 80
        assertEquals(itens, pedido.getItens());
    }

    @Test
    void calcularValorTotal_DeveRetornarValorCorreto() {
        BigDecimal valorTotal = pedido.calcularValorTotal(itens);

        assertEquals(BigDecimal.valueOf(80.0), valorTotal); // 2 * 10 + 3 * 20 = 80
    }

    @Test
    void calcularValorTotal_DeveRetornarZero_QuandoListaDeItensVazia() {
        BigDecimal valorTotal = pedido.calcularValorTotal(List.of());

        assertEquals(BigDecimal.ZERO, valorTotal);
    }
}