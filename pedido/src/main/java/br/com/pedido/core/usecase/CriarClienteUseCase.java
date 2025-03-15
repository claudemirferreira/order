package br.com.pedido.core.usecase;


import br.com.pedido.core.domain.ClienteDomain;


public interface CriarClienteUseCase {
    ClienteDomain executar(ClienteDomain clienteDomain);
}