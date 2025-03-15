package br.com.pedido.core.usecase;


import br.com.pedido.core.domain.Pedido;

public interface CriarPedidoUseCase {
    Pedido executar(Pedido pedido);
}