//package com.apispring;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import javax.validation.ConstraintViolationException;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import com.apiDelivery.api.domain.model.Cozinha;
//import com.apiDelivery.api.domain.service.CozinhaService;
//
//
//@ActiveProfiles("test")
//@SpringBootTest
//public class SpringApiDeliveryApplicationTests {
//	
//	@Autowired  
//	private CozinhaService cozinhaService;
//	
//	@Test
//	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
//	  
//		
//		Cozinha cozinha = new Cozinha();
//	    cozinha.setNome(null);
//
//	    ConstraintViolationException erro = 
//	            Assertions.assertThrows(ConstraintViolationException.class, () -> {
//	                cozinhaService.cadastrarCozinha(cozinha);
//	    });
//	    assertThat(erro).isNotNull();
//				
//		}
//	}
//	
//	
