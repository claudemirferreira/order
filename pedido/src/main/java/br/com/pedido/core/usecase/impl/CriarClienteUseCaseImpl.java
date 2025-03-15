package br.com.pedido.core.usecase.impl;

import br.com.pedido.core.domain.ClienteDomain;
import br.com.pedido.core.gateways.ClienteGateway;
import br.com.pedido.core.usecase.CriarClienteUseCase;
import org.springframework.stereotype.Service;

@Service
public class CriarClienteUseCaseImpl implements CriarClienteUseCase {

    private final ClienteGateway clienteGateway;

    public CriarClienteUseCaseImpl(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public ClienteDomain executar(ClienteDomain clienteDomain) {
        if (clienteDomain == null) {
            throw new IllegalArgumentException("ClienteDomain não pode ser nulo.");
        }

        // Validações adicionais podem ser feitas aqui
        if (clienteDomain.getNome() == null || clienteDomain.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente é obrigatório.");
        }

        if (clienteDomain.getEmail() == null || clienteDomain.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email do cliente é obrigatório.");
        }

        return clienteGateway.criar(clienteDomain);
    }

}
