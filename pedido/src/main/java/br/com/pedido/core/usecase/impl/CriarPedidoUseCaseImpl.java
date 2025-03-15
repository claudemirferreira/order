package br.com.pedido.core.usecase.impl;

import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.core.usecase.CriarPedidoUseCase;
import org.springframework.stereotype.Service;

@Service
public class CriarPedidoUseCaseImpl implements CriarPedidoUseCase {

    @Override
    public PedidoDomain executar(PedidoDomain pedido) {
        return null;
    }

}
