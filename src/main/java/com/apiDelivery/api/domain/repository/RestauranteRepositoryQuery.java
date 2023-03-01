package com.apiDelivery.api.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.apiDelivery.api.domain.model.Restaurante;

public interface RestauranteRepositoryQuery {

	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

}