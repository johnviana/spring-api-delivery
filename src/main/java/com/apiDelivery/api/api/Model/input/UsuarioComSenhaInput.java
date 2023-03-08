package com.apiDelivery.api.api.Model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioComSenhaInput extends UsuarioInput{

	@NotBlank
	private String senha;
	
}
