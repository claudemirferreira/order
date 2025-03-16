package br.com.pagamento.infrastructure.messaging;

import br.com.pagamento.application.dto.PedidoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PedidoConsumer {

    @KafkaListener(topics = "pedido-topic", groupId = "pedido-group")
    public void consumirPedido(PedidoDTO pedido) {
        log.info("{}", pedido);
    }
}