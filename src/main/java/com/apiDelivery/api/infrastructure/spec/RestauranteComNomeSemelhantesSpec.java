package com.apiDelivery.api.infrastructure.spec;


import org.springframework.data.jpa.domain.Specification;

import com.apiDelivery.api.domain.model.Restaurante;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestauranteComNomeSemelhantesSpec implements Specification<Restaurante>{


	private static final long serialVersionUID = 1L;

	private String nome;
	
	@Override
	public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		// TODO Auto-generated method stub
		return builder.like(root.get("nome"), "%" + nome + "%");	
	}

}
