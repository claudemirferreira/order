package br.com.pedido.infrastructure.persistence.gateways;

import br.com.pedido.core.domain.ClienteDomain;
import br.com.pedido.core.gateways.ClienteGateway;
import br.com.pedido.infrastructure.persistence.entity.Cliente;
import br.com.pedido.infrastructure.persistence.repository.ClienteRepository;
import jakarta.persistence.PersistenceException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class ClienteGatewayImpl implements ClienteGateway {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    public ClienteGatewayImpl(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<ClienteDomain> criar(ClienteDomain clienteDomain) {
        if (Objects.isNull(clienteDomain)) {
            log.warn("Tentativa de criar um cliente com dados nulos.");
            return Optional.empty();
        }

        try {
            Cliente cliente = convertToEntity(clienteDomain);
            Cliente clienteSalvo = salvarCliente(cliente);
            return Optional.of(convertToDomain(clienteSalvo));
        } catch (DataAccessException e) {
            log.error("Erro ao salvar o cliente no banco de dados: {}", clienteDomain, e);
            throw new PersistenceException("Erro ao salvar o cliente no banco de dados", e);
        }
    }

    private Cliente convertToEntity(ClienteDomain clienteDomain) {
        return modelMapper.map(clienteDomain, Cliente.class);
    }

    private ClienteDomain convertToDomain(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDomain.class);
    }

    private Cliente salvarCliente(Cliente cliente) {
        Cliente clienteSalvo = clienteRepository.save(cliente);
        log.info("Cliente criado com sucesso: {}", clienteSalvo.getId());
        return clienteSalvo;
    }
}