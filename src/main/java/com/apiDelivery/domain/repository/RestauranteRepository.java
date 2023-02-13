package com.apiDelivery.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apiDelivery.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>
, RestauranteRepositoryQuery, JpaSpecificationExecutor<Restaurante>{

//	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	@Query("from Restaurante r LEFT JOIN r.cozinha")
	List<Restaurante> findAll();
	
	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);
	
	@Query("FROM Restaurante where nome like %:nome and cozinha.id = :id")
	List<Restaurante> buscarPorNomeIdCozinha(String nome, @Param("id") Long cozinhaId);
	

}
