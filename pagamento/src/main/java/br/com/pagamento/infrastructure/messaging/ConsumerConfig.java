package br.com.pagamento.infrastructure.messaging;

import br.com.pagamento.application.dto.PedidoDTO;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.MessageListener;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerConfig {
    private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, "pedido-group");
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(org.springframework.kafka.support.serializer.JsonDeserializer.TRUSTED_PACKAGES, "*"); // Defina o pacote de classes permitidos
        props.put(org.springframework.kafka.support.serializer.JsonDeserializer.VALUE_DEFAULT_TYPE, PedidoDTO.class);
        return props;
    }

    @Bean
    public ConcurrentMessageListenerContainer<String, PedidoDTO> messageListenerContainer() {
        ContainerProperties containerProps = new ContainerProperties("pedido-topic-0");
        containerProps.setMessageListener(new MessageListener<String, PedidoDTO>() {
            @Override
            public void onMessage(ConsumerRecord<String, PedidoDTO> record) {
                // Process the message
            }
        });

        // Configure o factory do consumidor Kafka
        ConsumerFactory<String, PedidoDTO> consumerFactory = new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                new org.springframework.kafka.support.serializer.JsonDeserializer<>(PedidoDTO.class));

        return new ConcurrentMessageListenerContainer<>(consumerFactory, containerProps);
    }

}
