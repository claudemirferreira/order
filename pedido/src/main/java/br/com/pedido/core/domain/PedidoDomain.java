package br.com.pedido.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PedidoDomain {

    private Long id;
    private Long clienteId;
    private LocalDateTime dataPedido;
    private String status;
    private BigDecimal valorTotal;
    private List<ItemPedidoDomain> itens;

    public PedidoDomain() {
        this.itens = new ArrayList<>();
    }

    // Construtor
    public PedidoDomain(Long clienteId, List<ItemPedidoDomain> itens) {
        this.clienteId = clienteId;
        this.dataPedido = LocalDateTime.now();
        this.status = "PENDENTE";
        this.itens = itens;
        this.valorTotal = calcularValorTotal(itens);
    }

    private BigDecimal calcularValorTotal(List<ItemPedidoDomain> itens) {
        return itens.stream()
                .map(item -> item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}