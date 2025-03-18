package br.com.pedido.infrastructure.messaging;

import br.com.pedido.application.dto.PagamentoMessagingDTO;
import br.com.pedido.core.usecase.AtualizarStatusPedidoUseCase;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PagamentoConsumer {

    private final AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;

    public PagamentoConsumer(AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase) {
        this.atualizarStatusPedidoUseCase = atualizarStatusPedidoUseCase;
    }

    @KafkaListener(topics = "pagamento-topic", groupId = "pedido-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, PagamentoMessagingDTO> message) {
        PagamentoMessagingDTO pedido = message.value();
        log.info("{}", pedido);
        this.atualizarStatusPedidoUseCase.executar(pedido.getPedidoId(), pedido.getStatusPagamento());
    }

}
