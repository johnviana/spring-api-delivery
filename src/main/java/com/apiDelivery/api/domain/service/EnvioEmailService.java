package com.apiDelivery.api.domain.service;

import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

public interface EnvioEmailService {

	void enviar(Mensagem mensagem);
	
	@Getter
	@Setter
	class Mensagem {
		private Set<String> destinatario;
		private String assunto;
		private String corpo;
		
		@Singular("variavel")
		private Map<String, Object> variaveis;
		
	}
	
}
