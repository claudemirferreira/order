package br.com.pedido.application.dto;

import br.com.pedido.core.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoMessagingDTO {
    private Long pedidoId;
    private Status statusPagamento;
}
