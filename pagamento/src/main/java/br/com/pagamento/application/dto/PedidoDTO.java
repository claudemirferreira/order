package br.com.pagamento.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    //Long clienteId,
    @JsonProperty("dataPedido")
    private LocalDateTime dataPedido;
    private String status;
    private BigDecimal valorTotal;
    private List<ItemPedidoDTO> itensPedido;
}