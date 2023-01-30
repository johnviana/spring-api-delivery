package com.apispring.domain.exception;

public class EntidadeNaoEncontradaExcepetion extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	public EntidadeNaoEncontradaExcepetion(String mensagem) {
		super(mensagem);
	}

}
