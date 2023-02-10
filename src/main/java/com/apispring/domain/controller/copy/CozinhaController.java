package com.apispring.domain.controller.copy;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.apispring.domain.exception.EntidadeEmUsoException;
import com.apispring.domain.exception.EntidadeNaoEncontradaExcepetion;
import com.apispring.domain.model.Cozinha;
import com.apispring.domain.repository.CozinhaRepository;
import com.apispring.domain.service.CozinhaService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@RestController
@RequestMapping("/api/cozinhas")
public class CozinhaController {

	@Autowired
	CozinhaService cozinhaService;

	@Autowired
	CozinhaRepository cozinhaRepository;
	
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
		return cozinhaService.buscarOuFalhar(id_cozinha);
	}
	
	@PostMapping("/")
	public ResponseEntity<Cozinha> salvarCozinhar(@RequestBody Cozinha cozinha ){
		Cozinha cozinhaSalva = cozinhaService.cadastrarCozinha(cozinha);
		return ResponseEntity.ok(cozinhaSalva);
		
	}
	
	@PutMapping("/{id_cozinha}")
	public Cozinha AtualizarCozinha(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha){
		
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
