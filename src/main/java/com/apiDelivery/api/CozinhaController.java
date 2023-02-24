package com.apiDelivery.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.apiDelivery.domain.model.Cozinha;
import com.apiDelivery.domain.repository.CozinhaRepository;
import com.apiDelivery.domain.service.CozinhaService;

@RestController
@RequestMapping(value = "/api/cozinhas")
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
	
	@PostMapping
	public ResponseEntity<Cozinha> salvarCozinhar(@RequestBody @Valid Cozinha cozinha ){
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
