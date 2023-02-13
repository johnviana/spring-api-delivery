package com.apiDelivery.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.apiDelivery.domain.model.Restaurante;

public interface RestauranteRepositoryQuery {

	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

}