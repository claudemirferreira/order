package br.com.pedido.application.dto;

public record ErrorResponse(
        String mensagem,
        int status
) {
}