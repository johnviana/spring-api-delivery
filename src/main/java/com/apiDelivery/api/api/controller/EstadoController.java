package com.apiDelivery.api.api.controller;

import java.util.List;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.apiDelivery.api.domain.model.Estado;
import com.apiDelivery.api.domain.service.EstadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/estados")
public class EstadoController {
	
	
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping
	public List<Estado> listarTodos(){
		return estadoService.buscarTodosEstado();
		
	}
	
	@GetMapping("/{id}")
	public Estado buscarEstado(@PathVariable Long id) {
		return estadoService.buscarOuFalhar(id);
		
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Estado salvar(@RequestBody @Valid Estado estado) {
		return estadoService.salvarEstado(estado);
	}
	
	@PutMapping("/{id}")
	public Estado atualizarEstado(@PathVariable Long id, 
			@RequestBody Estado estado) {
		
		Estado estadoAtual = estadoService.buscarOuFalhar(id);
		
		BeanUtils.copyProperties(estado, estadoAtual, "id");
		return estadoService.salvarEstado(estadoAtual);
		
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		 estadoService.excluirEstado(id);
	}
	 

}
