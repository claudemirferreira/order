package br.com.pedido.adapter.input;

import br.com.pedido.application.dto.ClienteDTO;
import br.com.pedido.application.dto.CriarClienteDTO;
import br.com.pedido.application.mapper.ClienteMapper;
import br.com.pedido.core.domain.ClienteDomain;
import br.com.pedido.core.usecase.CriarClienteUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final CriarClienteUseCase criarClienteUseCase;
    private final ClienteMapper clienteMapper;

    public ClienteController(CriarClienteUseCase criarClienteUseCase, ClienteMapper clienteMapper) {
        this.criarClienteUseCase = criarClienteUseCase;
        this.clienteMapper = clienteMapper;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> criarCliente(@Valid @RequestBody CriarClienteDTO dto) {
        ClienteDomain clienteDomain = clienteMapper.toDomain(dto);
        ClienteDomain clienteSalvo = criarClienteUseCase.executar(clienteDomain);
        return ResponseEntity.ok(this.clienteMapper.toDTO(clienteSalvo));
    }
}