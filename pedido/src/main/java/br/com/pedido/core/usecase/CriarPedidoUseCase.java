package br.com.pedido.core.usecase;


import br.com.pedido.core.domain.PedidoDomain;

public interface CriarPedidoUseCase {
    PedidoDomain executar(PedidoDomain pedidoDomain);
}