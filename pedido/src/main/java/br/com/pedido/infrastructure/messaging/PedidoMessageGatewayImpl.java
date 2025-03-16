package br.com.pedido.infrastructure.messaging;

import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.core.gateways.PedidoMessageGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PedidoMessageGatewayImpl implements PedidoMessageGateway {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String topic;

    public PedidoMessageGatewayImpl(KafkaTemplate<String, Object> kafkaTemplate,
                                    @Value("${kafka.topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void send(PedidoDomain pedido) {
        log.info("==============================================================");
        log.info("{}", pedido);
        kafkaTemplate.send(this.topic, pedido);
    }

}
