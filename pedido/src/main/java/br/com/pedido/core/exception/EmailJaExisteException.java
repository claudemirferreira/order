package br.com.pedido.core.exception;

public class EmailJaExisteException extends RuntimeException {
    public EmailJaExisteException(String mensagem) {
        super(mensagem);
    }
}