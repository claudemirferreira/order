package br.com.pedido.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CriarClienteDTO(
        @NotNull(message = "O nome do produto é obrigatório.")
        String nome,
        @NotNull(message = "O email do produto é obrigatório.")
        @Email(message = "O email deve ser válido.")
        String email,
        @NotNull(message = "O endereço do produto é obrigatório.")
        String endereco ) {
}
