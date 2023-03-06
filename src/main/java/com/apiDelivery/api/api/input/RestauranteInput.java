package com.apiDelivery.api.api.input;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteInput {
	
	@NotBlank
	private String nome;
	
	@NotNull
	private BigDecimal taxaFrete;
	
	@NotNull
	@Valid
	private CozinhaIdInput cozinha;

}
