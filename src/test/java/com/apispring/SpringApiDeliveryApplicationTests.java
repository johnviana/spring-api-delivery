package com.apispring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.apiDelivery.domain.model.Cozinha;
import com.apiDelivery.domain.service.CozinhaService;

@SpringBootTest
public class SpringApiDeliveryApplicationTests {
	
	@Autowired
	@Qualifier("")
	public CozinhaService cozinhaService;
	
		@Test
		public void testarCadastroCozinhaSemNome() {

			Cozinha novaCozinha = new Cozinha();
			novaCozinha.setNome("Paudalho");
			
			novaCozinha = cozinhaService.cadastrarCozinha(novaCozinha);
		}
				
	}
	
	
