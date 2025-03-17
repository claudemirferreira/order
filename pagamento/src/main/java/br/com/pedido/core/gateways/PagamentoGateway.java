package br.com.pedido.core.gateways;

import br.com.pedido.core.domain.PagamentoDomain;

public interface PagamentoGateway {

    PagamentoDomain salvar(PagamentoDomain pagamentoDomain);
}
