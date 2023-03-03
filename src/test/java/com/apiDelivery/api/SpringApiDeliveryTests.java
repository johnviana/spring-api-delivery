package com.apiDelivery.api;

import static org.hamcrest.CoreMatchers.equalToObject;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.apiDelivery.api.domain.model.Cozinha;
import com.apiDelivery.api.domain.repository.CozinhaRepository;
import com.apiDelivery.api.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource("/application-test.properties")
class SpringApiDeliveryTests {
	
	@Autowired
	DatabaseCleaner dataBase;
	
	@Autowired
	CozinhaRepository cozinhaRepository;
	
	@LocalServerPort
	private int port;
	
	// Metodo: ordem  de executa primeiro
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/api/cozinhas";
		
		dataBase.clearTables();
		prepararDados();
	}
	
	
	@Test
	@DisplayName("Verifica se o estado é 200 quando consultar a Cozinha")
	public void deveRetornarStatus200_QuandoConsultarCozinha() {
		
		RestAssured.given()
		.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	@DisplayName("Verificar no banco de dandos se tem 2 Registro cadastrado")
	public void deveConter2Cozinhas_QuandoConsultarCozinhas() {
		
		RestAssured.given()
		.accept(ContentType.JSON)
		.when()
			.get()
		.then()
		 	.body("", Matchers.hasSize(2));
//		 	.body("nome", Matchers.hasItems("Indiana","Tailandesa"));
		
	}
	
	@Test
	@DisplayName("Verifica uma cozinha cadastrada e retorna status 201")
	public void deveRetornarStatus201_QuandoCadastrarCozinha() {
		RestAssured.given()
			.body("{ \"nome\": \"paudalho\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	@DisplayName("Verifica o Id se exite quando consultar pelo ID")
	private void deveRetornarStatusCorreto_QuandoIdCozinhaExistir() {
		
		RestAssured.given()
			.pathParam("cozinhaId", 2)
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalToObject("WANDERSON"));
	}
	
	@Test
	@DisplayName("Verifica se contem uma cozinha inexistente")
	private void deveRetornarRespostaStatus404_QuandoIdCozinhaInexistente() {
		
		RestAssured.given()
			.pathParam("cozinhaId", 100)
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
			
	}

	private void prepararDados() {
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("john");
		cozinhaRepository.save(cozinha1);
		
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("WANDERSON");
		cozinhaRepository.save(cozinha);
		
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
