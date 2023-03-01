package com.apiDelivery.api;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SpringApiDeliveryTests {
	
	@LocalServerPort
	private int port;
	
	// Metodo: ordem  de executa primeiro
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/api/cozinhas";
	}
	
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinha() {
		
		RestAssured.given()
		.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveConter4Cozinhas_QuandoConsultarCozinhas() {
		
		RestAssured.given()
		.accept(ContentType.JSON)
		.when()
			.get()
		.then()
		 	.body("", Matchers.hasSize(4))
		 	.body("nome", Matchers.hasItems("Indiana","Tailandesa"));
		
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarCozinha() {
		RestAssured.given()
			.body("{ \"nome\": \"Chinesa\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	
//	@Test
//	public void testarNome() {
//		
//		Cozinha novaCozinha = new Cozinha();
//		novaCozinha.setNome("macarrao");
//		
//		novaCozinha = cadastroCozinha.cadastrarCozinha(novaCozinha);
//		
//		assertThat(novaCozinha).isNotNull();
//		assertThat(novaCozinha.getId()).isNotNull();
//	}
//	
//	
//	@Test
//	public void testarCadastroCozinhaSemNome() {
//		Cozinha novaCozinha = new Cozinha();
//		novaCozinha.setNome(null);
//
//		ConstraintViolationException erroEsperado =
//				Assertions.assertThrows(ConstraintViolationException.class, () -> {
//					cadastroCozinha.cadastrarCozinha(novaCozinha);
//				});
//
//		assertThat(erroEsperado).isNotNull();
//	}
//	
//	@Test
//	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
//
//		EntidadeEmUsoException erroEsperado =
//				Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
//					cadastroCozinha.excluirCozinha(1L);
//				});
//
//		assertThat(erroEsperado).isNotNull();
//
//	}
//	
//	@Test
//	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
//		
//		EntidadeNaoEncontradaExcepetion erroEsperado = 
//				Assertions.assertThrows(EntidadeNaoEncontradaExcepetion.class, () -> {
//					cadastroCozinha.excluirCozinha(100L);
//				});
//		assertThat(erroEsperado).isNotNull();
//		
//	}
		
}
