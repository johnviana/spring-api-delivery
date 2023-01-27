package com.apispring.domain.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.apispring.domain.model.Cozinha;
import com.apispring.domain.service.CozinhaService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@RestController
@RequestMapping("/api/cozinhas")
public class CozinhaController {

	@Autowired
	CozinhaService cozinhaService;
	
	@GetMapping
	public List<Cozinha> listartodos(){
		return cozinhaService.listarCozinhas();
	}
	
	@GetMapping("/{id_cozinha}")
	public ResponseEntity<?> buscarCozinha(@PathVariable Long id_cozinha) {
		Optional<Cozinha> cozinha = cozinhaService.burcarCozinha(id_cozinha);
		
		if(cozinha.isPresent()) {
			return ResponseEntity.ok(cozinha);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/")
	public ResponseEntity<Cozinha> salvarCozinhar(@RequestBody Cozinha cozinha ){
		Cozinha cozinhaSalva = cozinhaService.cadastrarCozinha(cozinha);
		return ResponseEntity.ok(cozinhaSalva);
		
	}
	
	@PutMapping("/{id_cozinha}")
	public ResponseEntity<Cozinha> AtualizarCozinha(@PathVariable Long id_cozinha, @RequestBody Cozinha cozinha){
		Optional<Cozinha> cozinhaAtual = cozinhaService.burcarCozinha(id_cozinha);
		
		if(cozinhaAtual != null) {
			BeanUtils.copyProperties(cozinha, cozinhaAtual.get(),"idCozinha");
			cozinhaService.cadastrarCozinha(cozinhaAtual.get());
			return ResponseEntity.ok(cozinhaAtual.get());			
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id_cozinha}")
	public ResponseEntity<?> removerCozinha(@PathVariable Long id_cozinha){
		try {
			Optional<Cozinha> cozinhaA = cozinhaService.burcarCozinha(id_cozinha);
			if(cozinhaA != null) {
				cozinhaService.excluirCozinha(cozinhaA.get());
				return ResponseEntity.noContent().build();						
			}
				return ResponseEntity.notFound().build();
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
		
		
		
	}
	 
	
}
