package com.apispring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.apiDelivery.domain.model.Cozinha;
import com.apiDelivery.domain.service.CozinhaService;

@SpringBootTest
@SpringBootConfiguration
class SpringApiDeliveryTests {
	
	@Autowired
	@Qualifier
	private CozinhaService cadastroCozinha;
	
	@Test
	public void testarNome() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("macarrao");
		
		novaCozinha = cadastroCozinha.cadastrarCozinha(novaCozinha);
		
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}

}
