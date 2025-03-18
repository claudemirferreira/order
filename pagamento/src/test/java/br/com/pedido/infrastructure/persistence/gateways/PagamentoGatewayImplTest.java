package br.com.pedido.infrastructure.persistence.gateways;

import br.com.pedido.core.domain.PagamentoDomain;
import br.com.pedido.core.enums.Status;
import br.com.pedido.infrastructure.persistence.entity.Pagamento;
import br.com.pedido.infrastructure.persistence.repository.PagamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PagamentoGatewayImplTest {

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PagamentoGatewayImpl pagamentoGatewayImpl;

    private PagamentoDomain pagamentoDomain;
    private Pagamento pagamento;

    @BeforeEach
    void setUp() {
        pagamentoDomain = PagamentoDomain
                .builder()
                .id(1L)
                .valorTotal(BigDecimal.valueOf(100))
                .statusPagamento(Status.PENDENTE)
                .build();

        pagamento = Pagamento
                .builder()
                .id(1L)
                .valorTotal(BigDecimal.valueOf(100))
                .statusPagamento(Status.PENDENTE)
                .build();
    }

    @Test
    void testSalvar() {
        when(modelMapper.map(pagamentoDomain, Pagamento.class)).thenReturn(pagamento);
        when(pagamentoRepository.save(pagamento)).thenReturn(pagamento);
        when(modelMapper.map(pagamento, PagamentoDomain.class)).thenReturn(pagamentoDomain);

        PagamentoDomain savedPagamentoDomain = pagamentoGatewayImpl.salvar(pagamentoDomain);

        verify(modelMapper).map(pagamentoDomain, Pagamento.class);
        verify(pagamentoRepository).save(pagamento);
        verify(modelMapper).map(pagamento, PagamentoDomain.class);

        assertNotNull(savedPagamentoDomain);
        assertEquals(pagamentoDomain.getId(), savedPagamentoDomain.getId());
        assertEquals(pagamentoDomain.getValorTotal(), savedPagamentoDomain.getValorTotal());
        assertEquals(pagamentoDomain.getStatusPagamento(), savedPagamentoDomain.getStatusPagamento());
    }
}
