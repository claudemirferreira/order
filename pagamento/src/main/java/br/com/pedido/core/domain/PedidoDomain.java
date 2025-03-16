package br.com.pedido.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDomain {
    private Long id;
    private ClienteDomain cliente;
    private LocalDateTime dataPedido;
    private String status;
    private BigDecimal valorTotal;
}
