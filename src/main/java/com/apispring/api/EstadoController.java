package com.apispring.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apispring.domain.model.Estado;
import com.apispring.domain.repository.EstadoRepository;
import com.apispring.domain.service.EstadoService;

@RestController
@RequestMapping(value = "/estado")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping
	public List<Estado> listarTodos(){
		return estadoService.buscarTodosEstado();
		
	}
	 

}
