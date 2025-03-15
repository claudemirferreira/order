package br.com.pedido.infrastructure.persistence.gateways;

import br.com.pedido.core.domain.ClienteDomain;
import br.com.pedido.infrastructure.persistence.entity.Cliente;
import br.com.pedido.infrastructure.persistence.repository.ClienteRepository;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteGatewayImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClienteGatewayImpl clienteGateway;

    private ClienteDomain clienteDomain;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        clienteDomain = new ClienteDomain("João Silva", "joao.silva@example.com", "Rua das Flores, 123");
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João Silva");
        cliente.setEmail("joao.silva@example.com");
        cliente.setEndereco("Rua das Flores, 123");
    }

    @Test
    void criar_DeveRetornarClienteDomain_QuandoDadosValidos() {
        // Arrange
        when(modelMapper.map(clienteDomain, Cliente.class)).thenReturn(cliente);
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(modelMapper.map(cliente, ClienteDomain.class)).thenReturn(clienteDomain);

        // Act
        ClienteDomain resultado = clienteGateway.criar(clienteDomain);

        // Assert
        assertNotNull(resultado);
        assertEquals(clienteDomain.getNome(), resultado.getNome());
        assertEquals(clienteDomain.getEmail(), resultado.getEmail());
        assertEquals(clienteDomain.getEndereco(), resultado.getEndereco());
        verify(modelMapper, times(1)).map(clienteDomain, Cliente.class);
        verify(clienteRepository, times(1)).save(cliente);
        verify(modelMapper, times(1)).map(cliente, ClienteDomain.class);
    }

    @Test
    void criar_DeveRetornarNull_QuandoClienteDomainNulo() {
        // Act
        ClienteDomain resultado = clienteGateway.criar(null);

        // Assert
        assertNull(resultado);
        verify(modelMapper, never()).map(any(), any());
        verify(clienteRepository, never()).save(any());
    }

    @Test
    void criar_DeveLancarPersistenceException_QuandoErroNoBancoDeDados() {
        // Arrange
        when(modelMapper.map(clienteDomain, Cliente.class)).thenReturn(cliente);
        when(clienteRepository.save(cliente)).thenThrow(new DataAccessException("Erro no banco de dados") {});

        // Act & Assert
        PersistenceException exception = assertThrows(PersistenceException.class, () -> {
            clienteGateway.criar(clienteDomain);
        });

        assertEquals("Erro ao salvar o cliente no banco de dados", exception.getMessage());
        verify(modelMapper, times(1)).map(clienteDomain, Cliente.class);
        verify(clienteRepository, times(1)).save(cliente);
        verify(modelMapper, never()).map(cliente, ClienteDomain.class);
    }
}