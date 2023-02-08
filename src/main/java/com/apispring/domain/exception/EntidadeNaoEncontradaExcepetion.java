package com.apispring.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaExcepetion extends RuntimeException {

	private static final long serialVersionUID = 1L;
		
	public EntidadeNaoEncontradaExcepetion(String mensagem) {
		super(mensagem);
	}

}
