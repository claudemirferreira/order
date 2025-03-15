package br.com.pedido.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDomain {

    private Long id;
    private Long produtoId;
    private Integer quantidade;
    private BigDecimal precoUnitario;

}