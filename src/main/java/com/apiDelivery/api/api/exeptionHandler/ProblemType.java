package com.apiDelivery.api.api.exeptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	DADOS_INVALIDOS("/dados_invalidos", "Dados Inválidos"),
	CORPO_DA_REQUISICAO_IMCOMPATIVEL("/corpo_da_requisição_incompativel", "Corpo Incompativel"),
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não Encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidad em Uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de Regras de Negocios");
	
	private String title;
	private String uri;

	
	ProblemType(String path, String uri){
		this.uri = "https://apidelivery.com.br" + path;
		this.title = title;
	}
}
