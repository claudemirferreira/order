package br.com.pedido.adapter.input.exception;

import br.com.pedido.application.dto.ErrorResponse;
import br.com.pedido.core.exception.EmailJaExisteException;
import br.com.pedido.core.exception.RecursoNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleRecursoNaoEncontradoException_ShouldReturn404() {
        // Arrange
        RecursoNaoEncontradoException exception = new RecursoNaoEncontradoException("Recurso não encontrado");

        // Act
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleRecursoNaoEncontradoException(exception);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Recurso não encontrado", response.getBody().mensagem());
        assertEquals(404, response.getBody().status());
    }

    @Test
    void handleEmailJaExisteException_ShouldReturn409() {
        // Arrange
        EmailJaExisteException exception = new EmailJaExisteException("Email já cadastrado");

        // Act
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleEmailJaExisteException(exception);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Email já cadastrado", response.getBody().mensagem());
        assertEquals(409, response.getBody().status());
    }

    @Test
    void handleIllegalArgumentException_ShouldReturn400() {
        // Arrange
        IllegalArgumentException exception = new IllegalArgumentException("Parâmetro inválido");

        // Act
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleIllegalArgumentException(exception);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Parâmetro inválido", response.getBody().mensagem());
        assertEquals(400, response.getBody().status());
    }

    @Test
    void handleGenericException_ShouldReturn500() {
        // Arrange
        Exception exception = new Exception("Erro interno");

        // Act
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleGenericException(exception);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Ocorreu um erro interno no servidor.", response.getBody().mensagem());
        assertEquals(500, response.getBody().status());
    }

    @Test
    void handlerMethodArgumentNotValidException_ShouldReturn400() {
        // Arrange
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        when(exception.getAllErrors()).thenReturn(List.of());

        // Act
        ResponseEntity<List<String>> response = globalExceptionHandler.handlerMethodArgumentNotValidException(exception);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void handlerMethodValidationException_ShouldReturn400() {
        // Arrange
        HandlerMethodValidationException exception = mock(HandlerMethodValidationException.class);
        when(exception.getAllErrors()).thenReturn(List.of());

        // Act
        ResponseEntity<List<String>> response = globalExceptionHandler.handlerMethodValidationException(exception);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }
}
