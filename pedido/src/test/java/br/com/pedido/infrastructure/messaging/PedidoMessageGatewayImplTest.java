package br.com.pedido.infrastructure.messaging;

import br.com.pedido.application.dto.PedidoMessageDTO;
import br.com.pedido.application.mapper.PagamentoMapper;
import br.com.pedido.core.domain.PedidoDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class PedidoMessageGatewayImplTest {

    @InjectMocks
    private PedidoMessageGatewayImpl pedidoMessageGateway;

    @Mock
    private PagamentoMapper pagamentoMapper;  // Mock do mapeador

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate; // Mock do KafkaTemplate

    @Mock
    private PedidoDomain pedidoDomain;  // Mock do pedido

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pedidoMessageGateway = new PedidoMessageGatewayImpl(pagamentoMapper, kafkaTemplate, "kafka.pedidoTopic");
    }

    @Test
    void shouldSendMessageToKafka() {
        PedidoMessageDTO pedidoMessageDTO = new PedidoMessageDTO(1L, 1L, LocalDateTime.now(), BigDecimal.valueOf(100));
        when(pagamentoMapper.toDTO(pedidoDomain)).thenReturn(pedidoMessageDTO);

        pedidoMessageGateway.send(pedidoDomain);

        verify(kafkaTemplate, times(1)).send("kafka.pedidoTopic", pedidoMessageDTO);
    }
}
