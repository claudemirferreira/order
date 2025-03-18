package br.com.pedido.core.gateways;

import br.com.pedido.core.domain.PedidoDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoGateway {
    PedidoDomain salvar(PedidoDomain pedido);
    PedidoDomain findById(Long id);
    Page<PedidoDomain> listarPedidos(Pageable pageable);
}