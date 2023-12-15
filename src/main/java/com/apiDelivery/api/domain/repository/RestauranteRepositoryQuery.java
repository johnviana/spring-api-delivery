package com.apiDelivery.api.domain.repository;

import com.apiDelivery.api.domain.model.Restaurante;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestauranteRepositoryQuery {

	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

}