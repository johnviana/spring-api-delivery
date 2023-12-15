package com.apiDelivery.api.infrastructure.spec;

import java.util.ArrayList;

import org.springframework.data.jpa.domain.Specification;

import com.apiDelivery.api.domain.model.Pedido;
import com.apiDelivery.api.domain.repository.filter.PedidoFilter;

import jakarta.persistence.criteria.Predicate;

public class PedidoSpecs {
	
	public static Specification<Pedido> usandoFiltro(PedidoFilter filtro){
		return (root, query, builder) -> {
			var predicates = new ArrayList<Predicate>();
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
		
	}
	
}
