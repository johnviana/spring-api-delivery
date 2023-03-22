package com.apiDelivery;
 
import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.apiDelivery.api.CozinhaController;
import com.apiDelivery.domain.model.Cozinha;
import com.apiDelivery.domain.service.CozinhaService;

@SpringBootTest
public class CadastroCozinhaIntegrationTests  {
	
	@Autowired  
    @Qualifier("ConzinhaService")
	private CozinhaService cozinhaService;
	
	@Test
	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
	   CozinhaService cozinhaService = new CozinhaService();
		
		Cozinha cozinha = new Cozinha();
	    cozinha.setNome("john");

	    ConstraintViolationException erro = 
	            Assertions.assertThrows(ConstraintViolationException.class, () -> {
	                cozinhaService.cadastrarCozinha(cozinha);
	    });
	    assertThat(erro).isNotNull();
				
		}
	}
	

	}
	
	
