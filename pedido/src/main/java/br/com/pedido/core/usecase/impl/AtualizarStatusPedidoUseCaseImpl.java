package br.com.pedido.core.usecase.impl;

import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.core.enums.Status;
import br.com.pedido.core.gateways.PedidoGateway;
import br.com.pedido.core.usecase.AtualizarStatusPedidoUseCase;
import org.springframework.stereotype.Service;

@Service
public class AtualizarStatusPedidoUseCaseImpl implements AtualizarStatusPedidoUseCase {

    private final PedidoGateway pedidoGateway;

    public AtualizarStatusPedidoUseCaseImpl(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public void executar(Long pedidoId, Status statusPagamento) {
        PedidoDomain pedidoDomain = pedidoGateway.findById(pedidoId);
        pedidoDomain.setStatus(statusPagamento);
        pedidoGateway.salvar(pedidoDomain);
    }

}
