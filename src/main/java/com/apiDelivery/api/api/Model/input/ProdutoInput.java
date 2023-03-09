package com.apiDelivery.api.api.Model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	@PositiveOrZero
	private BigDecimal preco;
	
	@NotNull
	private boolean ativo;
}
