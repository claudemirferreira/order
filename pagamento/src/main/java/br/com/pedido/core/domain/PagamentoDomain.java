package br.com.pedido.core.domain;

import br.com.pedido.core.enums.Status;
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
public class PagamentoDomain {

    private Long id;

    private Long pedidoId;

    private Long clienteId;

    private LocalDateTime dataPedido;

    private LocalDateTime dataPagamento;

    private BigDecimal valorTotal;

    private Status statusPagamento;

}
