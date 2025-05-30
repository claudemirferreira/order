package br.com.pedido.core.usecase.impl;

import br.com.pedido.core.domain.ClienteDomain;
import br.com.pedido.core.domain.ItemPedidoDomain;
import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.core.gateways.ClienteGateway;
import br.com.pedido.core.gateways.PedidoGateway;
import br.com.pedido.core.gateways.PedidoMessageGateway;
import br.com.pedido.core.usecase.CriarPedidoUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriarPedidoUseCaseImpl implements CriarPedidoUseCase {

    private final PedidoGateway pedidoGateway;
    private final ClienteGateway clienteGateway;
    private final PedidoMessageGateway pedidoMessageGateway;


    public CriarPedidoUseCaseImpl(
            PedidoGateway pedidoGateway,
            ClienteGateway clienteGateway,
            PedidoMessageGateway pedidoMessageGateway) {
        this.pedidoGateway = pedidoGateway;
        this.clienteGateway = clienteGateway;
        this.pedidoMessageGateway = pedidoMessageGateway;
    }

    @Override
    public PedidoDomain executar(Long clienteId, List<ItemPedidoDomain> itens) {
        ClienteDomain clienteDomain = clienteGateway.findById(clienteId);
        PedidoDomain pedido = new PedidoDomain(clienteDomain, itens);
        var pedidoSalvo = pedidoGateway.salvar(pedido);
        pedidoMessageGateway.send(pedidoSalvo);
        return pedidoSalvo;
    }

}
