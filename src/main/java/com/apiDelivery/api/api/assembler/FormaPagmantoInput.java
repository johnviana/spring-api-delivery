package com.apiDelivery.api.api.assembler;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagmantoInput {
	
	@NotBlank
	private String descricao;

}
