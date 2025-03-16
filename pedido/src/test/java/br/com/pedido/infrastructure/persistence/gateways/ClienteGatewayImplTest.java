package br.com.pedido.infrastructure.persistence.gateways;

import br.com.pedido.core.domain.ClienteDomain;
import br.com.pedido.core.exception.EmailJaExisteException;
import br.com.pedido.core.exception.RecursoNaoEncontradoException;
import br.com.pedido.infrastructure.persistence.entity.Cliente;
import br.com.pedido.infrastructure.persistence.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;

import java.util.Optional;

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

    private Long clienteId;
    private Cliente clienteEntity;
    private ClienteDomain clienteDomain;

    @BeforeEach
    void setUp() {
        clienteId = 1L;
        clienteEntity = new Cliente();
        clienteEntity.setId(clienteId);
        clienteEntity.setEmail("teste@email.com");

        clienteDomain = new ClienteDomain();
        clienteDomain.setId(clienteId);
        clienteDomain.setEmail("teste@email.com");
    }

    @Test
    void criar_DeveSalvarERetornarClienteDomain_SeNaoExistirErro() {
        when(modelMapper.map(clienteDomain, Cliente.class)).thenReturn(clienteEntity);
        when(clienteRepository.save(clienteEntity)).thenReturn(clienteEntity);
        when(modelMapper.map(clienteEntity, ClienteDomain.class)).thenReturn(clienteDomain);

        ClienteDomain resultado = clienteGateway.criar(clienteDomain);

        assertNotNull(resultado);
        assertEquals(clienteDomain.getEmail(), resultado.getEmail());

        verify(clienteRepository, times(1)).save(clienteEntity);
        verify(modelMapper, times(2)).map(any(), any());
    }

    @Test
    void criar_DeveLancarEmailJaExisteException_SeErroDeBancoAcontecer() {
        when(modelMapper.map(clienteDomain, Cliente.class)).thenReturn(clienteEntity);
        when(clienteRepository.save(clienteEntity)).thenThrow(new DataAccessException("Erro no banco") {});

        EmailJaExisteException exception = assertThrows(EmailJaExisteException.class, () -> 
            clienteGateway.criar(clienteDomain)
        );

        assertTrue(exception.getMessage().contains("Erro email " + clienteDomain.getEmail() + " já existe"));
        verify(clienteRepository, times(1)).save(clienteEntity);
    }

    @Test
    void criar_DeveRetornarNulo_SeClienteDomainForNulo() {
        ClienteDomain resultado = clienteGateway.criar(null);
        assertNull(resultado);
    }

    @Test
    void findById_DeveRetornarClienteDomain_SeClienteExistir() {
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(clienteEntity));
        when(modelMapper.map(clienteEntity, ClienteDomain.class)).thenReturn(clienteDomain);

        ClienteDomain resultado = clienteGateway.findById(clienteId);

        assertNotNull(resultado);
        assertEquals(clienteId, resultado.getId());
        assertEquals(clienteDomain.getEmail(), resultado.getEmail());

        verify(clienteRepository, times(1)).findById(clienteId);
        verify(modelMapper, times(1)).map(clienteEntity, ClienteDomain.class);
    }

    @Test
    void findById_DeveLancarExcecao_SeClienteNaoExistir() {
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.empty());

        RecursoNaoEncontradoException exception = assertThrows(RecursoNaoEncontradoException.class, () -> 
            clienteGateway.findById(clienteId)
        );

        assertEquals("Cliente não encontrado com o ID: " + clienteId, exception.getMessage());
        verify(clienteRepository, times(1)).findById(clienteId);
        verifyNoInteractions(modelMapper);
    }
}
