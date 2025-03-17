package br.com.pedido.infrastructure.persistence.entity;

import br.com.pedido.core.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pagamento_id")
    private Long id;

    @Column(nullable = false)
    private Long pedidoId;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @Column(name = "data_pedido", nullable = false)
    private LocalDateTime dataPedido;

    @Column(nullable = false)
    private LocalDateTime dataPagamento;

    @Enumerated(EnumType.STRING)
    private Status statusPagamento;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

}
