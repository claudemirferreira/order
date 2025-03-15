package br.com.pedido.application.mapper;

import br.com.pedido.application.dto.PedidoDTO;
import br.com.pedido.core.domain.Pedido;

public class PedidoMapper {
    public static PedidoDTO toDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setCliente(pedido.getCliente());
        dto.setProduto(pedido.getProduto());
        dto.setQuantidade(pedido.getQuantidade());
        return dto;
    }
}