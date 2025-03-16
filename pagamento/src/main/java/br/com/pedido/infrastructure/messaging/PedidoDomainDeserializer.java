package br.com.pedido.infrastructure.messaging;

import br.com.pedido.core.domain.PedidoDomain;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class PedidoDomainDeserializer extends JsonDeserializer<PedidoDomain> {
    public PedidoDomainDeserializer() {
        super(PedidoDomain.class);
        this.addTrustedPackages("*");
    }
}
