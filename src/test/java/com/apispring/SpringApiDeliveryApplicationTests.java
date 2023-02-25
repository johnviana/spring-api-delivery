package com.apispring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.apiDelivery.domain.model.Cozinha;
import com.apiDelivery.domain.service.CozinhaService;

@SpringBootTest
@SpringBootApplication
@ContextConfiguration(locations = "/test-context.xml")
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
	
	
