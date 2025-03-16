package br.com.pedido.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoDTO(
        Long id,
        Long clienteId,
        LocalDateTime dataPedido,
        String status,
        BigDecimal valorTotal,
        List<ItemPedidoDTO> itensPedido) {
}