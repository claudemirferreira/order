package br.com.pedido.infrastructure.persistence.gateways;

import br.com.pedido.application.mapper.ItemPedidoMapper;
import br.com.pedido.application.mapper.PedidoMapper;
import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.core.exception.RecursoNaoEncontradoException;
import br.com.pedido.core.gateways.PedidoGateway;
import br.com.pedido.infrastructure.persistence.entity.ItemPedido;
import br.com.pedido.infrastructure.persistence.entity.Pedido;
import br.com.pedido.infrastructure.persistence.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class PedidoGatewayImpl implements PedidoGateway {

    private final PedidoRepository pedidoRepository;
    private final ModelMapper modelMapper;
    private final ItemPedidoMapper itemPedidoMapper;
    private final PedidoMapper pedidoMapper;

    public PedidoGatewayImpl(
            PedidoRepository pedidoRepository,
            ModelMapper modelMapper,
            ItemPedidoMapper itemPedidoMapper,
            PedidoMapper pedidoMapper) {
        this.pedidoRepository = pedidoRepository;
        this.modelMapper = modelMapper;
        this.itemPedidoMapper = itemPedidoMapper;
        this.pedidoMapper = pedidoMapper;
    }

    @Override
    public PedidoDomain salvar(PedidoDomain pedidoDomain) {
        Objects.requireNonNull(pedidoDomain, "PedidoDomain não pode ser nulo.");
        Pedido pedidoEntity = toEntity(pedidoDomain);
        var entity = pedidoRepository.save(pedidoEntity);
        return modelMapper.map(entity, PedidoDomain.class);
    }

    @Override
    public PedidoDomain findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado com o ID: " + id));
        return modelMapper.map(pedido, PedidoDomain.class);
    }

    public Pedido toEntity(PedidoDomain pedidoDomain) {
        Pedido pedidoEntity = modelMapper.map(pedidoDomain, Pedido.class);
        List<ItemPedido> itensEntity = itemPedidoMapper.toEntity(pedidoDomain.getItens());
        itensEntity.forEach(item -> item.setPedido(pedidoEntity));
        pedidoEntity.setItens(itensEntity);
        return pedidoEntity;
    }

    public Page<PedidoDomain> listarPedidos(Pageable pageable) {
        return pedidoRepository.findAll(pageable)
                .map(pedidoMapper::toDomain);
    }
}