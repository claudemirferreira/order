package br.com.pedido.infrastructure.messaging;

import br.com.pedido.application.dto.PedidoMessageDTO;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class PedidoDomainDeserializer extends JsonDeserializer<PedidoMessageDTO> {
    public PedidoDomainDeserializer() {
        super(PedidoMessageDTO.class);
        this.addTrustedPackages("*");
    }
}
