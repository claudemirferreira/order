package br.com.pedido.application.mapper;

import br.com.pedido.application.dto.ClienteDTO;
import br.com.pedido.application.dto.CriarClienteDTO;
import br.com.pedido.core.domain.ClienteDomain;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteDomain toDomain(CriarClienteDTO dto) {
        return new ClienteDomain(
                dto.nome(),
                dto.email(),
                dto.endereco()
        );
    }

    public ClienteDTO toDTO(ClienteDomain clienteDomain) {
        return new ClienteDTO(
                clienteDomain.getId(),
                clienteDomain.getNome(),
                clienteDomain.getEmail(),
                clienteDomain.getEndereco()
        );
    }
}