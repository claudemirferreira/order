package br.com.pedido.adapter.input;

import br.com.pedido.application.dto.ItemPedidoDTO;
import br.com.pedido.application.dto.PedidoDTO;
import br.com.pedido.application.mapper.ItemPedidoMapper;
import br.com.pedido.application.mapper.PedidoMapper;
import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.core.usecase.CriarPedidoUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final CriarPedidoUseCase criarPedidoUseCase;
    private final ItemPedidoMapper itemPedidoMapper;
    private final PedidoMapper pedidoMapper;

    public PedidoController(CriarPedidoUseCase criarPedidoUseCase, ItemPedidoMapper itemPedidoMapper, PedidoMapper pedidoMapper) {
        this.criarPedidoUseCase = criarPedidoUseCase;
        this.itemPedidoMapper = itemPedidoMapper;
        this.pedidoMapper = pedidoMapper;
    }


    @PostMapping(value = "/{clienteId}")
    public ResponseEntity<PedidoDTO> criarPedido( @PathVariable Long clienteId,  @RequestBody @Valid List<ItemPedidoDTO> itensPedido) {
        PedidoDomain pedidoDomain = criarPedidoUseCase.executar( clienteId, itemPedidoMapper.toDomain(itensPedido));
        return ResponseEntity.ok(pedidoMapper.toDTO( pedidoDomain));
    }

}