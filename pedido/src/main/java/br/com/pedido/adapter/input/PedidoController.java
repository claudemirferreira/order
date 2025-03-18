package br.com.pedido.adapter.input;

import br.com.pedido.application.dto.ItemPedidoDTO;
import br.com.pedido.application.dto.PedidoDTO;
import br.com.pedido.application.mapper.ItemPedidoMapper;
import br.com.pedido.application.mapper.PedidoMapper;
import br.com.pedido.core.domain.PedidoDomain;
import br.com.pedido.core.usecase.CriarPedidoUseCase;
import br.com.pedido.core.usecase.ListarPedidoUseCase;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final CriarPedidoUseCase criarPedidoUseCase;
    private final ItemPedidoMapper itemPedidoMapper;
    private final PedidoMapper pedidoMapper;
    private final ListarPedidoUseCase listarPedidoUseCase;

    public PedidoController(
            CriarPedidoUseCase criarPedidoUseCase,
            ItemPedidoMapper itemPedidoMapper,
            PedidoMapper pedidoMapper,
            ListarPedidoUseCase listarPedidoUseCase) {
        this.criarPedidoUseCase = criarPedidoUseCase;
        this.itemPedidoMapper = itemPedidoMapper;
        this.pedidoMapper = pedidoMapper;
        this.listarPedidoUseCase = listarPedidoUseCase;
    }


    @PostMapping(value = "/{clienteId}")
    public ResponseEntity<PedidoDTO> criarPedido( @PathVariable Long clienteId,  @RequestBody @Valid List<ItemPedidoDTO> itensPedido) {
        PedidoDomain pedidoDomain = criarPedidoUseCase.executar( clienteId, itemPedidoMapper.toDomain(itensPedido));
        return ResponseEntity.ok(pedidoMapper.toDTO( pedidoDomain));
    }

    @GetMapping
    public ResponseEntity<Page<PedidoDTO>> listarPedidos(Pageable pageable) {
        var pedidosDomain = listarPedidoUseCase.executar(pageable);
        return ResponseEntity.ok(pedidoMapper.toDTOPage(pedidosDomain));
    }

}