package br.com.pedido.core.usecase.impl;

import br.com.pedido.core.domain.Pedido;
import br.com.pedido.core.usecase.CriarPedidoUseCase;
import org.springframework.stereotype.Service;

@Service
public class CriarPedidoUseCaseImpl implements CriarPedidoUseCase {

    @Override
    public Pedido executar(Pedido pedido) {
        return null;
    }

}
