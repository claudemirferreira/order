package br.com.pedido.infrastructure.messaging;

import br.com.pedido.core.domain.ItemPedidoDomain;
import br.com.pedido.core.domain.PedidoDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoMessageGatewayImplTest {

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private PedidoMessageGatewayImpl pedidoMessageGateway;

    private final String topic = "pedido-topic"; // Valor simulado para kafka.topic

    @BeforeEach
    void setUp() {
        pedidoMessageGateway = new PedidoMessageGatewayImpl(kafkaTemplate, topic);
    }

    @Test
    void testSend_Sucesso() {
        PedidoDomain pedido = new PedidoDomain();
        pedido.setId(1L);
        pedido.setItens(Collections.singletonList(new ItemPedidoDomain(1L, 1L, 1, BigDecimal.valueOf( 5000.0))));

        pedidoMessageGateway.send(pedido);

        verify(kafkaTemplate, times(1)).send(topic, pedido);
    }
}