package br.com.pedido.application.dto;

public record ClienteDTO(
        Long id,
        String nome,
        String email,
        String endereco ) {
}
