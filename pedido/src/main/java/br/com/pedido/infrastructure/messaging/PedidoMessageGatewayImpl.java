package br.com.pedido.infrastructure.messaging;

import br.com.pedido.application.dto.PedidoMessageDTO;
import br.com.pedido.application.mapper.PagamentoMapper;
import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.core.gateways.PedidoMessageGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PedidoMessageGatewayImpl implements PedidoMessageGateway {

    private final PagamentoMapper pagamentoMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String topic;

    public PedidoMessageGatewayImpl(PagamentoMapper pagamentoMapper, KafkaTemplate<String, Object> kafkaTemplate,
                                    @Value("${kafka.pedidoTopic}") String topic) {
        this.pagamentoMapper = pagamentoMapper;
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void send(PedidoDomain pedido) {
        var dto = pagamentoMapper.toDTO(pedido);
        log.info("{}", dto);
        kafkaTemplate.send(this.topic, dto);
    }

}
