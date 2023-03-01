package com.apiDelivery.api.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntidadedeNogocio extends RuntimeException {

	private static final long serialVersionUID = 1L;
		
	public EntidadedeNogocio(String mensagem) {
		super(mensagem);
	}

}
