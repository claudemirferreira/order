package br.com.pedido.infrastructure.persistence.repository;

import br.com.pedido.infrastructure.persistence.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
