package br.com.pedido.core.usecase;

import br.com.pedido.core.domain.PagamentoDomain;

public interface EfetuarPagamentoUsecase {

    void execute(PagamentoDomain pagamentoDomain);
}
