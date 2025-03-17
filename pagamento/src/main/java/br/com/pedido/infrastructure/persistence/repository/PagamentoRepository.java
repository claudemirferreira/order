package br.com.pedido.infrastructure.persistence.repository;

import br.com.pedido.infrastructure.persistence.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
