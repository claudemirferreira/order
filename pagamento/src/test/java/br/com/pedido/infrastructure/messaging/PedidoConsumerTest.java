package br.com.pedido.infrastructure.messaging;

import br.com.pedido.application.dto.PedidoMessageDTO;
import br.com.pedido.application.mapper.PagamentoMapper;
import br.com.pedido.core.domain.PagamentoDomain;
import br.com.pedido.core.enums.Status;
import br.com.pedido.core.usecase.EfetuarPagamentoUsecase;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoConsumerTest {

    @Mock
    private EfetuarPagamentoUsecase efetuarPagamentoUsecase;

    @Mock
    private PagamentoMapper pagamentoMapper;

    @Mock
    private Logger logger; // Mock logger if needed for verifying logs

    @InjectMocks
    private PedidoConsumer pedidoConsumer;

    private PedidoMessageDTO pedidoMessageDTO;
    private ConsumerRecord<String, PedidoMessageDTO> consumerRecord;

    @BeforeEach
    void setUp() {
        pedidoMessageDTO = PedidoMessageDTO
                .builder()
                .id(1L)
                .valorTotal(BigDecimal.valueOf(100))
                .build();

        consumerRecord = new ConsumerRecord<>("pedido-topic", 0, 0, "key", pedidoMessageDTO);
    }

    @Test
    void testConsume() {
        PagamentoDomain pagamentoDomain = PagamentoDomain
                .builder()
                .id(1L)
                .valorTotal(BigDecimal.valueOf(100))
                .statusPagamento(Status.PENDENTE)
                .build();

        when(pagamentoMapper.toDomain(pedidoMessageDTO)).thenReturn(pagamentoDomain);

        pedidoConsumer.consume(consumerRecord);

        verify(pagamentoMapper).toDomain(pedidoMessageDTO);
        verify(efetuarPagamentoUsecase).execute(pagamentoDomain);
    }
}
