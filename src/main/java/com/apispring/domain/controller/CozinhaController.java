package com.apispring.domain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apispring.domain.model.Cozinha;
import com.apispring.domain.service.CozinhaService;

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
	public Cozinha buscarCozinha(@PathVariable Long id_cozinha) {
		return cozinhaService.burcarCozinha(id_cozinha);
	}
	 
	
}
