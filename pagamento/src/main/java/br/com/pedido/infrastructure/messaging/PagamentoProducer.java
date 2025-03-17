package br.com.pedido.infrastructure.messaging;

import br.com.pedido.core.domain.PagamentoDomain;
import br.com.pedido.core.gateways.PagamentoMessagingGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PagamentoProducer implements PagamentoMessagingGateway {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String topic;

    public PagamentoProducer(KafkaTemplate<String, Object> kafkaTemplate,
                             @Value("${kafka.pagamentoTopic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void send(PagamentoDomain pagamentoDomain) {
        log.info("==============================================================");
        log.info("{}", pagamentoDomain);
        kafkaTemplate.send(this.topic, pagamentoDomain);
    }

}
