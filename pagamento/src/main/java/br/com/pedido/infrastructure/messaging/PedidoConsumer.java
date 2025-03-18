package br.com.pedido.infrastructure.messaging;

import br.com.pedido.application.dto.PedidoMessageDTO;
import br.com.pedido.application.mapper.PagamentoMapper;
import br.com.pedido.core.domain.PagamentoDomain;
import br.com.pedido.core.usecase.EfetuarPagamentoUsecase;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PedidoConsumer {

    private final EfetuarPagamentoUsecase efetuarPagamentoUsecase;
    private final PagamentoMapper pagamentoMapper;

    public PedidoConsumer(EfetuarPagamentoUsecase efetuarPagamentoUsecase, PagamentoMapper pagamentoMapper) {
        this.efetuarPagamentoUsecase = efetuarPagamentoUsecase;
        this.pagamentoMapper = pagamentoMapper;
    }

    @KafkaListener(topics = "pedido-topic", groupId = "pedido-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, PedidoMessageDTO> message) {
        PedidoMessageDTO pedido = message.value();
        log.info("{}", pedido);
        PagamentoDomain pagamentoDomain = pagamentoMapper.toDomain(pedido);
        efetuarPagamentoUsecase.execute(pagamentoDomain);
    }
}