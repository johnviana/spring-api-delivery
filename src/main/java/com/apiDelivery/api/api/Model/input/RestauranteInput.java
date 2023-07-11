package com.apiDelivery.api.api.Model.input;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	
	@Valid
	@NotNull
	private EnderecoInput endereco;

}
