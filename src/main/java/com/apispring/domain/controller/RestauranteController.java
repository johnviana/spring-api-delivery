package com.apispring.domain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apispring.domain.model.Restaurante;
import com.apispring.domain.service.RestauranteService;

@RestController
@RequestMapping(value = "api/restaurantes")
public class RestauranteController {
	
	@Autowired
	RestauranteService restauranteService;
	
	@GetMapping
	public List<Restaurante> listarTodosRestaurantes(){
		return restauranteService.listarRestuarantes();
	}

}
