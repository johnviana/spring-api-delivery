package com.apispring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.apiDelivery.domain.model.Cozinha;
import com.apiDelivery.domain.repository.CozinhaRepository;
import com.apiDelivery.domain.service.CozinhaService;


@ContextConfiguration
class SpringApiDeliveryApplicationTests {

	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Test
	public void deveAtribuirId_quandoCadastrarCozinhaComDadosCorretos() {
		// cenário
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");
		
		// ação
		novaCozinha = cozinhaRepository.save(novaCozinha);
			
		
	}
	
	

}
