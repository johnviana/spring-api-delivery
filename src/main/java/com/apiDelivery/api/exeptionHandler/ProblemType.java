package com.apiDelivery.api.exeptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não Encontrada");
	
	private String title;
	private String uri;

	
	ProblemType(String path, String uri){
		this.uri = "https://apidelivery.com.br" + path;
		this.title = title;
	}
}
