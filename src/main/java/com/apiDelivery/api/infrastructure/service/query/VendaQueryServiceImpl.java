package com.apiDelivery.api.infrastructure.service.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.apiDelivery.api.domain.filter.VendaDiariaFilter;
import com.apiDelivery.api.domain.model.Pedido;
import com.apiDelivery.api.domain.model.StatusPedido;
import com.apiDelivery.api.domain.model.dto.VendaDiaria;
import com.apiDelivery.api.domain.service.VendaQueryService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Predicate;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(VendaDiaria.class);
		var root = query.from(Pedido.class);
		var predicates = new ArrayList<Predicate>();
		
		var functionConvertTzDataCriacao = builder.function(
				"convert_tz", Date.class, root.get("dataCriacao"),
				builder.literal("+00:00"), builder.literal(timeOffset));
		
		var functionDateDataCriacao = builder.function(
				"date", Date.class, functionConvertTzDataCriacao);
		
		var selection = builder.construct(VendaDiaria.class,
				functionDateDataCriacao,
				builder.count(root.get("id")),
				builder.sum(root.get("valorTotal")));
		
		if (filtro.getRestauranteId() != null) {
			predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
		}
	      
		if (filtro.getDataCriacaoInicio() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), 
					filtro.getDataCriacaoInicio()));
		}

		if (filtro.getDataCriacaoFim() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), 
					filtro.getDataCriacaoFim()));
		}
	      
		predicates.add(root.get("status").in(
				StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));
		
		query.select(selection);
		query.where(predicates.toArray(new Predicate[0]));
		query.groupBy(functionDateDataCriacao);
		
		return manager.createQuery(query).getResultList();
	}

}