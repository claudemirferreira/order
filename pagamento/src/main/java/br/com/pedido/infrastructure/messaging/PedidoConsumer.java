package br.com.pedido.infrastructure.messaging;

import br.com.pedido.core.domain.PedidoDomain;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PedidoConsumer {


    @KafkaListener(topics = "pedido-topic", groupId = "pedido-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, PedidoDomain> record) {
        PedidoDomain pedido = record.value();
        log.info("{}", pedido);
    }
}