package com.apispring.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.apispring.domain.model.Estado;
import com.apispring.domain.service.EstadoService;

@RestController
@RequestMapping(value = "/estados")
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
	public Estado salvar(@RequestBody Estado estado) {
		return estadoService.salvarEstado(estado);
	}
	 

}
