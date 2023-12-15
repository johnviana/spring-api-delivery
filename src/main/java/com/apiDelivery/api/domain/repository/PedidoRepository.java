package com.apiDelivery.api.domain.repository;

import com.apiDelivery.api.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	Optional<Pedido> findByCodigo(String codigo);
	
}
