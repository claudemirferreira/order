package br.com.pedido.infrastructure.persistence.repository;

import br.com.pedido.infrastructure.persistence.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
