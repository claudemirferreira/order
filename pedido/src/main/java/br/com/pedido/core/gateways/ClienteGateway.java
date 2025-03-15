package br.com.pedido.core.gateways;

import br.com.pedido.core.domain.ClienteDomain;

import java.util.Optional;

public interface ClienteGateway {

    Optional<ClienteDomain> criar(ClienteDomain clienteDomain);

}
