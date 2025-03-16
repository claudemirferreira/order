package br.com.pedido.core.gateways;

import br.com.pedido.core.domain.PedidoDomain;

public interface PedidoGateway {
    PedidoDomain salvar(PedidoDomain pedido);
}