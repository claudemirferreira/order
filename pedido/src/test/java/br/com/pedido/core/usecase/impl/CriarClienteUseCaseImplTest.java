package br.com.pedido.core.usecase.impl;

import br.com.pedido.core.domain.ClienteDomain;
import br.com.pedido.core.gateways.ClienteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarClienteUseCaseImplTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private CriarClienteUseCaseImpl criarClienteUseCase;

    private ClienteDomain clienteDomain;

    @BeforeEach
    void setUp() {
        clienteDomain = new ClienteDomain("João Silva", "joao.silva@example.com", "Rua das Flores, 123");
    }

    @Test
    void executar_DeveRetornarClienteDomain_QuandoDadosValidos() {
        // Arrange
        when(clienteGateway.criar(clienteDomain)).thenReturn(clienteDomain);

        // Act
        ClienteDomain resultado = criarClienteUseCase.executar(clienteDomain);

        // Assert
        assertNotNull(resultado);
        assertEquals(clienteDomain.getNome(), resultado.getNome());
        assertEquals(clienteDomain.getEmail(), resultado.getEmail());
        assertEquals(clienteDomain.getEndereco(), resultado.getEndereco());
        verify(clienteGateway, times(1)).criar(clienteDomain);
    }

    @Test
    void executar_DeveLancarExcecao_QuandoClienteDomainNulo() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            criarClienteUseCase.executar(null);
        });

        assertEquals("ClienteDomain não pode ser nulo.", exception.getMessage());
        verify(clienteGateway, never()).criar(any());
    }

    @Test
    void executar_DeveLancarExcecao_QuandoNomeInvalido() {
        // Arrange
        clienteDomain.setNome(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            criarClienteUseCase.executar(clienteDomain);
        });

        assertEquals("Nome do cliente é obrigatório.", exception.getMessage());
        verify(clienteGateway, never()).criar(any());
    }

    @Test
    void executar_DeveLancarExcecao_QuandoEmailInvalido() {
        // Arrange
        clienteDomain.setEmail(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            criarClienteUseCase.executar(clienteDomain);
        });

        assertEquals("Email do cliente é obrigatório.", exception.getMessage());
        verify(clienteGateway, never()).criar(any());
    }

    @Test
    void executar_DevePropagarExcecao_QuandoGatewayLancaExcecao() {
        // Arrange
        when(clienteGateway.criar(clienteDomain)).thenThrow(new RuntimeException("Erro no gateway"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            criarClienteUseCase.executar(clienteDomain);
        });

        assertEquals("Erro no gateway", exception.getMessage());
        verify(clienteGateway, times(1)).criar(clienteDomain);
    }
}