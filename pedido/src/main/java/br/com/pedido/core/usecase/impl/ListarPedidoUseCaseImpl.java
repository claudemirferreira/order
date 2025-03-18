package br.com.pedido.core.usecase.impl;

import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.core.gateways.PedidoGateway;
import br.com.pedido.core.usecase.ListarPedidoUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarPedidoUseCaseImpl implements ListarPedidoUseCase {

    private final PedidoGateway pedidoGateway;

    public ListarPedidoUseCaseImpl(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public Page<PedidoDomain> executar(Pageable pageable) {
        return pedidoGateway.listarPedidos(pageable);
    }

}
