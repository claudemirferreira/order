package br.com.pedido.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ItemPedidoDTO(
        @NotNull(message = "O ID do produto é obrigatório.")
        Long produtoId,

        @NotNull(message = "A quantidade é obrigatória.")
        @Min(value = 1, message = "A quantidade deve ser no mínimo 1.")
        Integer quantidade,

        @NotNull(message = "O preço unitário é obrigatório.")
        @Min(value = 0, message = "O preço unitário deve ser um valor positivo.")
        BigDecimal precoUnitario
) {
}
