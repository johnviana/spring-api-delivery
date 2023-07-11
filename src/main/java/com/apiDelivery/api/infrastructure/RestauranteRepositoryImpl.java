package com.apiDelivery.api.infrastructure;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;


import org.springframework.stereotype.Repository;

import com.apiDelivery.api.domain.model.Restaurante;
import com.apiDelivery.api.domain.repository.RestauranteRepositoryQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQuery {
	
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, 
			BigDecimal taxaFreteFinal){
		
		
		var jpql = new StringBuilder(); 
		jpql.append("from Restaurante where 0 = 0 ");
		
		var parametros = new HashMap<String, Object> ();
		
		if(nome != null && nome != "" ) {
			jpql.append("and nome like :nome ");
			parametros.put("nome", "%" + nome + "%");
		}
		if (taxaFreteInicial != null) {
			jpql.append("and taxaFrete >= :taxaInicial ");
			parametros.put("taxaInicial", taxaFreteInicial);
		}
		if(taxaFreteFinal != null) {
			jpql.append("and taxaFrete <= :taxaFinal ");
			parametros.put("taxaFinal", taxaFreteFinal);
		}
		
		TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);
				
				parametros.forEach((chave , valor ) -> query.setParameter(chave, valor));
				return query.getResultList();
	}

}
