package com.apiDelivery.api.infrastructure.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.apiDelivery.api.domain.model.Restaurante;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class RestauranteComFreteGratisSpec implements Specification<Restaurante>{


	private static final long serialVersionUID = 1L;

	@Override
	public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		// TODO Auto-generated method stub
		return builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);	
	}

}
