package com.apiDelivery.api.domain.repository;

import com.apiDelivery.api.domain.model.Produto;
import com.apiDelivery.api.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	@Query("from Produto where restaurante.id = :restaurante and id = :produto")
	Optional<Produto> findbyId(@Param("restaurante") Long restauranteId,
			@Param("produto") Long produtoId);
	
	
	List<Produto> findByRestaurante(Restaurante restaurante);

}
