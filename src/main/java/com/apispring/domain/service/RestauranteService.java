package com.apispring.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apispring.domain.model.Restaurante;
import com.apispring.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {
	
	@Autowired
	RestauranteRepository restauranteRepository;
	
	
	public List<Restaurante> listarRestuarantes(){
		return restauranteRepository.findAll();
	}

}
