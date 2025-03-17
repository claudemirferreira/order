package br.com.pedido.application.mapper;

import br.com.pedido.application.dto.PedidoDTO;
import br.com.pedido.application.dto.PedidoMessageDTO;
import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.infrastructure.persistence.entity.Pedido;
import org.springframework.stereotype.Component;


@Component
public class PedidoMapper {

    private final ItemPedidoMapper itemPedidoMapper;

    public PedidoMapper(ItemPedidoMapper itemPedidoMapper) {
        this.itemPedidoMapper = itemPedidoMapper;
    }

    public PedidoDTO toDTO(PedidoDomain pedidoDomain) {
        return new PedidoDTO(
                pedidoDomain.getId(),
                pedidoDomain.getCliente().getId(),
                pedidoDomain.getDataPedido(),
                pedidoDomain.getStatus(),
                pedidoDomain.getValorTotal(),
                itemPedidoMapper.toDTO(pedidoDomain.getItens())
        );
    }

    public Pedido toEntity(PedidoDomain pedidoDomain) {
        return Pedido
                .builder()
                .id(pedidoDomain.getId())
                .clienteId(pedidoDomain.getCliente().getId())
                .dataPedido(pedidoDomain.getDataPedido())
                .status(pedidoDomain.getStatus())
                .valorTotal(pedidoDomain.getValorTotal())
                .build();
    }

    public PedidoMessageDTO toDomain(PedidoDTO pedidoDTO) {
        return PedidoMessageDTO
                .builder()
                .clienteId(pedidoDTO.clienteId())
                .valorTotal(pedidoDTO.valorTotal())
                .dataPedido(pedidoDTO.dataPedido())
                .build();
    }
}