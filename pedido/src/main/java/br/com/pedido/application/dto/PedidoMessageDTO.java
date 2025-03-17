package br.com.pedido.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoMessageDTO {
    private Long id;
    private Long clienteId;
    private LocalDateTime dataPedido;
    private String status;
    private BigDecimal valorTotal;
}