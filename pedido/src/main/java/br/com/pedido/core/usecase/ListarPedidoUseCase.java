package br.com.pedido.core.usecase;


import br.com.pedido.core.domain.PedidoDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListarPedidoUseCase {

    Page<PedidoDomain> executar(Pageable pageable);

}