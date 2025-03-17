package br.com.pedido.core.usecase.impl;

import br.com.pedido.core.domain.PagamentoDomain;
import br.com.pedido.core.enums.Status;
import br.com.pedido.core.gateways.PagamentoGateway;
import br.com.pedido.core.usecase.EfetuarPagamentoUsecase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class EfetuarPagamentoUsecaseImpl implements EfetuarPagamentoUsecase {

    private final PagamentoGateway pagamentoGateway;

    public EfetuarPagamentoUsecaseImpl(PagamentoGateway pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
    }

    @Override
    public void execute(PagamentoDomain pagamentoDomain) {
        log.info("{}", pagamentoDomain);
        pagamentoDomain.setDataPagamento(LocalDateTime.now());
        pagamentoDomain.setStatusPagamento(Status.PAGO);
        log.info("{}", pagamentoDomain);
        pagamentoGateway.salvar(pagamentoDomain);

    }

}
