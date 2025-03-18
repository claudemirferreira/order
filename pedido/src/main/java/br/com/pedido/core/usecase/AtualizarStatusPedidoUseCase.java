package br.com.pedido.core.usecase;


import br.com.pedido.core.enums.Status;

public interface AtualizarStatusPedidoUseCase {

    void executar(Long pedidoId, Status statusPagamento);
}