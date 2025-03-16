package br.com.pedido.core.gateways;

import br.com.pedido.core.domain.PedidoDomain;

public interface PedidoMessageGateway {
    void send(PedidoDomain pedido);
}