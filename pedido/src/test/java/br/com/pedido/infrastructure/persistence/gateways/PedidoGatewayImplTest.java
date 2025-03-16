package br.com.pedido.infrastructure.persistence.gateways;

import br.com.pedido.application.mapper.ItemPedidoMapper;
import br.com.pedido.core.domain.ClienteDomain;
import br.com.pedido.core.domain.ItemPedidoDomain;
import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.infrastructure.persistence.entity.Cliente;
import br.com.pedido.infrastructure.persistence.entity.ItemPedido;
import br.com.pedido.infrastructure.persistence.entity.Pedido;
import br.com.pedido.infrastructure.persistence.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoGatewayImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ItemPedidoMapper itemPedidoMapper;

    @InjectMocks
    private PedidoGatewayImpl pedidoGateway;

    private PedidoDomain pedidoDomain;
    private Pedido pedidoEntity;
    private ItemPedidoDomain itemPedidoDomain;
    private ItemPedido itemPedidoEntity;

    @BeforeEach
    void setUp() {
        // Criando instâncias de domínio
        itemPedidoDomain = new ItemPedidoDomain(1L, 1L, 2, BigDecimal.valueOf(10.0));
        pedidoDomain = new PedidoDomain(ClienteDomain.builder().id(1L).build(), List.of(itemPedidoDomain));

        // Criando instâncias de entidade
        itemPedidoEntity = new ItemPedido();
        itemPedidoEntity.setProdutoId(1L);
        itemPedidoEntity.setQuantidade(2);
        itemPedidoEntity.setPrecoUnitario(BigDecimal.valueOf(10.0));

        pedidoEntity = new Pedido();
        pedidoEntity.setId(1L);
        pedidoEntity.setItens(Collections.singletonList(itemPedidoEntity));

        // Configurando mocks
        lenient().when(modelMapper.map(pedidoDomain, Pedido.class)).thenReturn(pedidoEntity);
        lenient().when(modelMapper.map(pedidoEntity, PedidoDomain.class)).thenReturn(pedidoDomain);
        lenient().when(itemPedidoMapper.toEntity(pedidoDomain.getItens())).thenReturn(Collections.singletonList(itemPedidoEntity));
        lenient().when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoEntity);

    }

    @Test
    void salvar_DeveSalvarPedidoERetornarPedidoDomain() {
        PedidoDomain resultado = pedidoGateway.salvar(pedidoDomain);

        assertNotNull(resultado);
        assertEquals(1, resultado.getItens().size());
        assertEquals(1L, resultado.getItens().get(0).getProdutoId());
        assertEquals(2, resultado.getItens().get(0).getQuantidade());

        verify(pedidoRepository, times(1)).save(any(Pedido.class));
        verify(modelMapper, times(1)).map(pedidoDomain, Pedido.class);
        verify(modelMapper, times(1)).map(pedidoEntity, PedidoDomain.class);
        verify(itemPedidoMapper, times(1)).toEntity(pedidoDomain.getItens());
    }

    @Test
    void salvar_DeveLancarExcecao_SePedidoDomainForNulo() {
        Exception exception = assertThrows(NullPointerException.class, () -> pedidoGateway.salvar(null));
        assertEquals("PedidoDomain não pode ser nulo.", exception.getMessage());
    }
}
