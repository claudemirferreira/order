package br.com.pedido.adapter.input;

import br.com.pedido.application.dto.ClienteDTO;
import br.com.pedido.application.dto.CriarClienteDTO;
import br.com.pedido.application.mapper.ClienteMapper;
import br.com.pedido.core.domain.ClienteDomain;
import br.com.pedido.core.usecase.CriarClienteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

    @Mock
    private CriarClienteUseCase criarClienteUseCase;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteController clienteController;

    private CriarClienteDTO criarClienteDTO;
    private ClienteDomain clienteDomain;
    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        criarClienteDTO = new CriarClienteDTO("João Silva", "joao.silva@example.com", "Rua das Flores, 123");
        clienteDomain = new ClienteDomain("João Silva", "joao.silva@example.com", "Rua das Flores, 123");
        clienteDTO = new ClienteDTO(1L, "João Silva", "joao.silva@example.com", "Rua das Flores, 123");
    }

    @Test
    void criarCliente_DeveRetornarClienteDTO_QuandoDadosValidos() {
        // Arrange
        when(clienteMapper.toDomain(criarClienteDTO)).thenReturn(clienteDomain);
        when(criarClienteUseCase.executar(clienteDomain)).thenReturn(clienteDomain);
        when(clienteMapper.toDTO(clienteDomain)).thenReturn(clienteDTO);

        // Act
        ResponseEntity<ClienteDTO> resposta = clienteController.criarCliente(criarClienteDTO);

        // Assert
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
        assertEquals(clienteDTO, resposta.getBody());
        verify(clienteMapper, times(1)).toDomain(criarClienteDTO);
        verify(criarClienteUseCase, times(1)).executar(clienteDomain);
        verify(clienteMapper, times(1)).toDTO(clienteDomain);
    }
}