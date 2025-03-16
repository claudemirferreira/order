package br.com.pedido.infrastructure.persistence.gateways;

import br.com.pedido.application.mapper.ItemPedidoMapper;
import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.core.gateways.PedidoGateway;
import br.com.pedido.infrastructure.persistence.entity.ItemPedido;
import br.com.pedido.infrastructure.persistence.entity.Pedido;
import br.com.pedido.infrastructure.persistence.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PedidoGatewayImpl implements PedidoGateway {

    private final PedidoRepository pedidoRepository;
    private final ModelMapper modelMapper;
    private final ItemPedidoMapper itemPedidoMapper;

    public PedidoGatewayImpl(
            PedidoRepository pedidoRepository,
            ModelMapper modelMapper,
            ItemPedidoMapper itemPedidoMapper) {
        this.pedidoRepository = pedidoRepository;
        this.modelMapper = modelMapper;
        this.itemPedidoMapper = itemPedidoMapper;
    }

    @Override
    public PedidoDomain salvar(PedidoDomain pedidoDomain) {
        Objects.requireNonNull(pedidoDomain, "PedidoDomain não pode ser nulo.");
        Pedido pedidoEntity = toEntity(pedidoDomain);
        Pedido pedidoSalvo = pedidoRepository.save(pedidoEntity);
        return modelMapper.map(pedidoEntity, PedidoDomain.class);
    }

    public Pedido toEntity(PedidoDomain pedidoDomain) {
        Pedido pedidoEntity = modelMapper.map(pedidoDomain, Pedido.class);
        List<ItemPedido> itensEntity = itemPedidoMapper.toEntity(pedidoDomain.getItens());
        itensEntity.forEach(item -> item.setPedido(pedidoEntity));
        pedidoEntity.setItens(itensEntity);
        return pedidoEntity;
    }
}