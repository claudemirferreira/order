package br.com.pedido.infrastructure.messaging;

import br.com.pedido.application.dto.PagamentoMessagingDTO;
import br.com.pedido.core.domain.PagamentoDomain;
import br.com.pedido.core.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagamentoProducerTest {

    @Mock
    private KafkaTemplate<String, PagamentoMessagingDTO> kafkaTemplate;

    @InjectMocks
    private PagamentoProducer pagamentoProducer;

    private PagamentoDomain pagamentoDomain;

    private String topic = "kafka.pagamentoTopic";  // Set the topic value directly

    @BeforeEach
    void setUp() {
        // Create a dummy PagamentoDomain instance
        pagamentoDomain = new PagamentoDomain();
        pagamentoDomain.setPedidoId(123L);
        pagamentoDomain.setStatusPagamento(Status.PENDENTE);

        // Initialize the producer with the mock KafkaTemplate and the topic
        pagamentoProducer = new PagamentoProducer(kafkaTemplate, topic);
    }

    @Test
    void testSend() {
        // Act: Call the send method to simulate sending a message
        pagamentoProducer.send(pagamentoDomain);

        // Assert: Verify that kafkaTemplate.send() was called with the correct parameters
        verify(kafkaTemplate).send(eq("kafka.pagamentoTopic"), any(PagamentoMessagingDTO.class));

        // Optionally, verify the message content that is being sent
        verify(kafkaTemplate).send(eq("kafka.pagamentoTopic"), argThat(dto ->
                dto.getPedidoId().equals(pagamentoDomain.getPedidoId()) &&
                        dto.getStatusPagamento().equals(pagamentoDomain.getStatusPagamento())
        ));
    }
}
