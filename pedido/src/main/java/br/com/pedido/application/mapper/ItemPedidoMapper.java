package br.com.pedido.application.mapper;

import br.com.pedido.application.dto.ItemPedidoDTO;
import br.com.pedido.core.domain.ItemPedidoDomain;
import br.com.pedido.infrastructure.persistence.entity.ItemPedido;
import br.com.pedido.infrastructure.persistence.entity.Pedido;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ItemPedidoMapper {

    public ItemPedidoDomain toDomain(ItemPedidoDTO itemPedidoDTO) {
        Objects.requireNonNull(itemPedidoDTO, "ItemPedidoDTO não pode ser nulo.");
        return ItemPedidoDomain.builder()
                .produtoId(itemPedidoDTO.produtoId())
                .quantidade(itemPedidoDTO.quantidade())
                .precoUnitario(itemPedidoDTO.precoUnitario())
                .build();
    }

    public List<ItemPedidoDomain> toDomain(List<ItemPedidoDTO> itens) {
        return itens.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public ItemPedidoDTO toDTO(ItemPedidoDomain itemPedidoDomain) {
        return new ItemPedidoDTO(
                itemPedidoDomain.getProdutoId(),
                itemPedidoDomain.getQuantidade(),
                itemPedidoDomain.getPrecoUnitario()
        );
    }

    public List<ItemPedidoDTO> toDTO(List<ItemPedidoDomain> itens) {
        return itens.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ItemPedido toEntity(ItemPedidoDomain itemPedidoDomain) {
        return ItemPedido
                .builder()
                .id(itemPedidoDomain.getId())
                .produtoId(itemPedidoDomain.getProdutoId())
                .precoUnitario(itemPedidoDomain.getPrecoUnitario())
                .quantidade(itemPedidoDomain.getQuantidade())
                .build();
    }

    public List<ItemPedido> toEntity(List<ItemPedidoDomain> itens) {
        return itens.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}