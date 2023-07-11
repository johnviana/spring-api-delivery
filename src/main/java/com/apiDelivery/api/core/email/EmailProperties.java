package com.apiDelivery.api.core.email;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("algafood.email")
public class EmailProperties {

	private Implementecao impl = Implementecao.FAKE;
	
	@NotNull
	private String remetente;
	
	private Sandbox sandbox = new Sandbox();
	
	public enum Implementecao{
		SMTP, FAKE, SANDBOX
	}

}
