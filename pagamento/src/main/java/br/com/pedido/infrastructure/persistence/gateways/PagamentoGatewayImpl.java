package br.com.pedido.infrastructure.persistence.gateways;

import br.com.pedido.core.domain.PagamentoDomain;
import br.com.pedido.core.gateways.PagamentoGateway;
import br.com.pedido.infrastructure.persistence.entity.Pagamento;
import br.com.pedido.infrastructure.persistence.repository.PagamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PagamentoGatewayImpl implements PagamentoGateway {

    private final PagamentoRepository pagamentoRepository;
    private final ModelMapper modelMapper;

    public PagamentoGatewayImpl(PagamentoRepository pagamentoRepository, ModelMapper modelMapper) {
        this.pagamentoRepository = pagamentoRepository;
        this.modelMapper = modelMapper;
    }

    public PagamentoDomain salvar(PagamentoDomain pagamentoDomain) {
        Pagamento pagamento = modelMapper.map(pagamentoDomain, Pagamento.class);
        pagamentoRepository.save(pagamento);
        return modelMapper.map(pagamento, PagamentoDomain.class);
    }

}
