package br.com.pagamento.infrastructure.messaging;

import br.com.pagamento.application.dto.PedidoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.serialization.Deserializer;

public class PedidoDomainDeserializer implements Deserializer<PedidoDTO> {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule()); // Registra o módulo JSR-310

    @Override
    public PedidoDTO deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, PedidoDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao desserializar PedidoDTO", e);
        }
    }
}