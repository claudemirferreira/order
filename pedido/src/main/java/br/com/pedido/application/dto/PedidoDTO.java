package br.com.pedido.application.dto;

import br.com.pedido.core.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private Long clienteId;
    private LocalDateTime dataPedido;
    private Status status;
    private BigDecimal valorTotal;
    private List<ItemPedidoDTO> itensPedido;
}