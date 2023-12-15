package com.apiDelivery.api.api.controller;

import com.apiDelivery.api.domain.model.Cozinha;
import com.apiDelivery.api.domain.repository.CozinhaRepository;
import com.apiDelivery.api.domain.service.CozinhaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaService cozinhaService;

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@GetMapping
	public List<Cozinha> listartodos(){
		return cozinhaService.listarCozinhas();
	}
	
	@GetMapping("/nome")
	public List<Cozinha> buscarPorNome(@RequestParam String nome){
		return cozinhaRepository.nome(nome); 
	}
	
	@GetMapping("nome/nome")
	public List<Cozinha> buscaTodosNomes(@RequestParam String nome){
		return cozinhaRepository.findTodosByNomeContaining(nome);
		
	}
	
	
	@GetMapping("/{id_cozinha}")
	public Cozinha buscarCozinha(@PathVariable Long id_cozinha) {
		Cozinha cozinha = cozinhaService.buscarOuFalhar(id_cozinha);
		
		cozinha.add(WebMvcLinkBuilder.linkTo(Cozinha.class)
				.slash(cozinha.getId()).withSelfRel());
		
		cozinha.add(WebMvcLinkBuilder.linkTo(Cozinha.class).withRel("cozinhas"));
		
//		cozinha.add(Link.of("http://localhost:8080/api/cozinhas/1"));
//		cozinha.add(Link.of("http://localhost:8080/api/cozinhas", "cozinhas"));
		return cozinha;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha salvarCozinhar(@RequestBody @Valid Cozinha cozinha ){
		Cozinha cozinhaSalva = cozinhaService.cadastrarCozinha(cozinha);
		return cozinhaService.cadastrarCozinha(cozinhaSalva);
		
	}
	
	@PutMapping("/{id_cozinha}")
	public Cozinha AtualizarCozinha(@PathVariable @Valid Long cozinhaId, @RequestBody Cozinha cozinha){
		
		Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(cozinhaId);
		
			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			return cozinhaService.cadastrarCozinha(cozinhaAtual);			
		
	}
	
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarCozinha(@PathVariable Long cozinhaId){
		cozinhaService.excluirCozinha(cozinhaId);
		
	}
	 	
}
