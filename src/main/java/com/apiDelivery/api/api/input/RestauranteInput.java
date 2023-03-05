package com.apiDelivery.api.api.input;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteInput {
	
	private String nome;
	
	private BigDecimal taxaFrete;
	
	private CozinhaIdInput cozinha;

}
