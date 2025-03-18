package br.com.pedido.infrastructure.messaging;

import br.com.pedido.application.dto.PagamentoMessagingDTO;
import br.com.pedido.core.enums.Status;
import br.com.pedido.core.usecase.AtualizarStatusPedidoUseCase;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.test.context.EmbeddedKafka;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@EnableKafka
@EmbeddedKafka(partitions = 1, topics = "pagamento-topic")
class PagamentoConsumerTest {

    @InjectMocks
    private PagamentoConsumer pagamentoConsumer; // A classe que estamos testando.

    @Mock
    private AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase; // Mock do use case.

    private ConsumerRecord<String, PagamentoMessagingDTO> message;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        PagamentoMessagingDTO pagamentoDTO = new PagamentoMessagingDTO(1L, Status.PAGO);
        message = new ConsumerRecord<>("pagamento-topic", 0, 0, "key", pagamentoDTO);
    }

    @Test
    void shouldCallAtualizarStatusPedidoUseCaseOnConsume() {
        pagamentoConsumer.consume(message);

        verify(atualizarStatusPedidoUseCase, times(1)).executar(1L, Status.PAGO);
    }

}
