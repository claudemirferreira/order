package br.com.pedido.application.mapper;

import br.com.pedido.application.dto.PedidoMessageDTO;
import br.com.pedido.core.domain.PedidoDomain;
import org.springframework.stereotype.Component;


@Component
public class PagamentoMapper {

    public PedidoMessageDTO toDTO(PedidoDomain pedidoDomain) {
        return PedidoMessageDTO
                .builder()
                .id(pedidoDomain.getId())
                .clienteId(pedidoDomain.getCliente().getId())
                .valorTotal(pedidoDomain.getValorTotal())
                .dataPedido(pedidoDomain.getDataPedido())
                .status(pedidoDomain.getStatus())
                .build();
    }
}