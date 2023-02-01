package com.apispring.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.apispring.domain.model.Restaurante;

public interface RestauranteRepositoryQuery {

	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

}