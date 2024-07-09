package com.apiDelivery.api.core.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@Valid
@ConfigurationProperties("algafood.auth")
public class AlgaFoodSecurityProperties {

	@NotBlank
	private String providerUrl;
	
}
