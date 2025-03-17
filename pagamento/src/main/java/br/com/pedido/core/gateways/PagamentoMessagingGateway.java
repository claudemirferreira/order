package br.com.pedido.core.gateways;

import br.com.pedido.core.domain.PagamentoDomain;

public interface PagamentoMessagingGateway {

    void send(PagamentoDomain pagamentoDomain);
}
