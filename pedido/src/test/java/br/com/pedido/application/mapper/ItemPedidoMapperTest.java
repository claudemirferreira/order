package br.com.pedido.application.mapper;

import br.com.pedido.application.dto.ItemPedidoDTO;
import br.com.pedido.core.domain.ItemPedidoDomain;
import br.com.pedido.infrastructure.persistence.entity.ItemPedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemPedidoMapperTest {

    private ItemPedidoMapper itemPedidoMapper;

    @BeforeEach
    void setUp() {
        itemPedidoMapper = new ItemPedidoMapper();
    }

    @Test
    void toDomain_DeveConverterItemPedidoDTOParaDomain() {
        ItemPedidoDTO dto = new ItemPedidoDTO(1L, 2, BigDecimal.valueOf(50.0));

        ItemPedidoDomain domain = itemPedidoMapper.toDomain(dto);

        assertNotNull(domain);
        assertEquals(dto.produtoId(), domain.getProdutoId());
        assertEquals(dto.quantidade(), domain.getQuantidade());
        assertEquals(dto.precoUnitario(), domain.getPrecoUnitario());
    }

    @Test
    void toDomainLista_DeveConverterListaDeDTOParaListaDeDomain() {
        List<ItemPedidoDTO> dtoList = List.of(
                new ItemPedidoDTO(1L, 2, BigDecimal.valueOf(50.0)),
                new ItemPedidoDTO(2L, 3, BigDecimal.valueOf(30.0))
        );

        List<ItemPedidoDomain> domainList = itemPedidoMapper.toDomain(dtoList);

        assertNotNull(domainList);
        assertEquals(2, domainList.size());
        assertEquals(dtoList.get(0).produtoId(), domainList.get(0).getProdutoId());
        assertEquals(dtoList.get(1).produtoId(), domainList.get(1).getProdutoId());
    }

    @Test
    void toDTO_DeveConverterItemPedidoDomainParaDTO() {
        ItemPedidoDomain domain = ItemPedidoDomain.builder()
                .produtoId(1L)
                .quantidade(2)
                .precoUnitario(BigDecimal.valueOf(50.0))
                .build();

        ItemPedidoDTO dto = itemPedidoMapper.toDTO(domain);

        assertNotNull(dto);
        assertEquals(domain.getProdutoId(), dto.produtoId());
        assertEquals(domain.getQuantidade(), dto.quantidade());
        assertEquals(domain.getPrecoUnitario(), dto.precoUnitario());
    }

    @Test
    void toDTOLista_DeveConverterListaDeDomainParaListaDeDTO() {
        List<ItemPedidoDomain> domainList = List.of(
                ItemPedidoDomain.builder().produtoId(1L).quantidade(2).precoUnitario(BigDecimal.valueOf(50.0)).build(),
                ItemPedidoDomain.builder().produtoId(2L).quantidade(3).precoUnitario(BigDecimal.valueOf(30.0)).build()
        );

        List<ItemPedidoDTO> dtoList = itemPedidoMapper.toDTO(domainList);

        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());
        assertEquals(domainList.get(0).getProdutoId(), dtoList.get(0).produtoId());
        assertEquals(domainList.get(1).getProdutoId(), dtoList.get(1).produtoId());
    }

    @Test
    void toEntity_DeveConverterItemPedidoDomainParaEntity() {
        ItemPedidoDomain domain = ItemPedidoDomain.builder()
                .id(1L)
                .produtoId(2L)
                .quantidade(3)
                .precoUnitario(BigDecimal.valueOf(40.0))
                .build();

        ItemPedido entity = itemPedidoMapper.toEntity(domain);

        assertNotNull(entity);
        assertEquals(domain.getId(), entity.getId());
        assertEquals(domain.getProdutoId(), entity.getProdutoId());
        assertEquals(domain.getQuantidade(), entity.getQuantidade());
        assertEquals(domain.getPrecoUnitario(), entity.getPrecoUnitario());
    }

    @Test
    void toEntityLista_DeveConverterListaDeDomainParaListaDeEntity() {
        List<ItemPedidoDomain> domainList = List.of(
                ItemPedidoDomain.builder().id(1L).produtoId(2L).quantidade(3).precoUnitario(BigDecimal.valueOf(40.0)).build(),
                ItemPedidoDomain.builder().id(2L).produtoId(3L).quantidade(1).precoUnitario(BigDecimal.valueOf(20.0)).build()
        );

        List<ItemPedido> entityList = itemPedidoMapper.toEntity(domainList);

        assertNotNull(entityList);
        assertEquals(2, entityList.size());
        assertEquals(domainList.get(0).getProdutoId(), entityList.get(0).getProdutoId());
        assertEquals(domainList.get(1).getProdutoId(), entityList.get(1).getProdutoId());
    }
}
