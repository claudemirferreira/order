package br.com.pedido.application.mapper;

import br.com.pedido.application.dto.ItemPedidoDTO;
import br.com.pedido.core.domain.ItemPedidoDomain;
import br.com.pedido.infrastructure.persistence.entity.ItemPedido;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Component
public class ItemPedidoMapper {

    public ItemPedidoDomain toDomain(ItemPedidoDTO itemPedidoDTO) {
        Objects.requireNonNull(itemPedidoDTO, "ItemPedidoDTO não pode ser nulo.");
        return ItemPedidoDomain.builder()
                .produtoId(itemPedidoDTO.getProdutoId())
                .quantidade(itemPedidoDTO.getQuantidade())
                .precoUnitario(itemPedidoDTO.getPrecoUnitario())
                .build();
    }

    public List<ItemPedidoDomain> toDomain(List<ItemPedidoDTO> itens) {
        return itens.stream()
                .map(this::toDomain)
                .collect(toList());
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
                .collect(toList());
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
                .collect(toList());
    }
}