package com.apiDelivery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.apiDelivery.domain.model.Cozinha;
import com.apiDelivery.domain.service.CozinhaService;

@SpringBootTest
@SpringBootConfiguration
@ContextConfiguration(classes = CozinhaService.class)
public class SpringApiDeliveryTests {
	
	@Autowired	public CozinhaService cozinhaService;
	
		@Test
		public void testarCadastroCozinhaSemNome() {

			Cozinha novaCozinha = new Cozinha();
			novaCozinha.setNome("Paudalho");
			
			novaCozinha = cozinhaService.cadastrarCozinha(novaCozinha);
		}
				
	}

