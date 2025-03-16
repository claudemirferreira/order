package br.com.pedido.core.gateways;

import br.com.pedido.core.domain.ClienteDomain;

public interface ClienteGateway {

    ClienteDomain criar(ClienteDomain clienteDomain);
    ClienteDomain findById(Long id);

}
