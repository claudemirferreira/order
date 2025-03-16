package br.com.pedido.core.usecase;


import br.com.pedido.core.domain.ItemPedidoDomain;
import br.com.pedido.core.domain.PedidoDomain;

import java.util.List;

public interface CriarPedidoUseCase {
    PedidoDomain executar(Long clienteId, List<ItemPedidoDomain> itens);
}