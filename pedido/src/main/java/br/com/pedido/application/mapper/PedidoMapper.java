package br.com.pedido.application.mapper;

import br.com.pedido.application.dto.PedidoDTO;
import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.infrastructure.persistence.entity.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    private final ModelMapper modelMapper;

    public PedidoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PedidoDomain toDomain(Pedido pedido) {
        return modelMapper.map(pedido, PedidoDomain.class);
    }

    public Pedido toEntity(PedidoDomain pedidoDomain) {
        return modelMapper.map(pedidoDomain, Pedido.class);
    }

    public PedidoDTO toDTO(PedidoDomain pedidoDomain) {
        return modelMapper.map(pedidoDomain, PedidoDTO.class);
    }

    public Page<PedidoDTO> toDTOPage(Page<PedidoDomain> pedidoDomainPage) {
        return pedidoDomainPage.map(this::toDTO);
    }
}
