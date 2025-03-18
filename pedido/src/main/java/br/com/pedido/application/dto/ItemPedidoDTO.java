package br.com.pedido.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDTO{

        @NotNull(message = "O ID do produto é obrigatório.")
        private Long produtoId;

        @NotNull(message = "A quantidade é obrigatória.")
        @Min(value = 1, message = "A quantidade deve ser no mínimo 1.")
        private Integer quantidade;

        @NotNull(message = "O preço unitário é obrigatório.")
        @Min(value = 0, message = "O preço unitário deve ser um valor positivo.")
        private BigDecimal precoUnitario;

}
