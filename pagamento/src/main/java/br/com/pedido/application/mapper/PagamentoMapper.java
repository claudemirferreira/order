package br.com.pedido.application.mapper;

import br.com.pedido.application.dto.PedidoMessageDTO;
import br.com.pedido.core.domain.PagamentoDomain;
import org.springframework.stereotype.Service;

@Service
public class PagamentoMapper {

    public PagamentoDomain toDomain(PedidoMessageDTO pedidoMessageDTO) {
        return PagamentoDomain
                .builder()
                .pedidoId(pedidoMessageDTO.getId())
                .clienteId(pedidoMessageDTO.getClienteId())
                .valorTotal(pedidoMessageDTO.getValorTotal())
                .dataPedido(pedidoMessageDTO.getDataPedido())
                .build();
    }

}
