package com.apiDelivery.api.domain.repository.filter;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class PedidoFilter {
	
	private Long clientId;
	private Long restauranteId;
	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataCriacaoFim;

}
