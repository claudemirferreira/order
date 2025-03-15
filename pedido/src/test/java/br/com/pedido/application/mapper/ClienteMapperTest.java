package br.com.pedido.application.mapper;

import br.com.pedido.application.dto.ClienteDTO;
import br.com.pedido.application.dto.CriarClienteDTO;
import br.com.pedido.core.domain.ClienteDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteMapperTest {

    private final ClienteMapper clienteMapper = new ClienteMapper();

    @Test
    void toDomain_DeveMapearCriarClienteDTOParaClienteDomain() {
        // Arrange
        CriarClienteDTO dto = new CriarClienteDTO("João Silva", "joao.silva@example.com", "Rua das Flores, 123");

        // Act
        ClienteDomain clienteDomain = clienteMapper.toDomain(dto);

        // Assert
        assertNotNull(clienteDomain);
        assertEquals(dto.nome(), clienteDomain.getNome());
        assertEquals(dto.email(), clienteDomain.getEmail());
        assertEquals(dto.endereco(), clienteDomain.getEndereco());
    }

    @Test
    void toDTO_DeveMapearClienteDomainParaClienteDTO() {
        // Arrange
        ClienteDomain clienteDomain = new ClienteDomain(1L, "João Silva", "joao.silva@example.com", "Rua das Flores, 123");

        // Act
        ClienteDTO clienteDTO = clienteMapper.toDTO(clienteDomain);

        // Assert
        assertNotNull(clienteDTO);
        assertEquals(clienteDomain.getId(), clienteDTO.id());
        assertEquals(clienteDomain.getNome(), clienteDTO.nome());
        assertEquals(clienteDomain.getEmail(), clienteDTO.email());
        assertEquals(clienteDomain.getEndereco(), clienteDTO.endereco());
    }
}