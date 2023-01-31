package com.apispring.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apispring.domain.exception.EntidadeEmUsoException;
import com.apispring.domain.exception.EntidadeNaoEncontradaExcepetion;
import com.apispring.domain.model.Cozinha;
import com.apispring.domain.model.Restaurante;
import com.apispring.domain.repository.CozinhaRepository;
import com.apispring.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {
	
	@Autowired
	RestauranteRepository restauranteRepository;
	
	@Autowired
	CozinhaRepository cozinhaRespository;
	
	
	public List<Restaurante> listarRestuarantes(){
		return restauranteRepository.findAll();
	}
	
	
	public Restaurante adicionar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRespository.findById(cozinhaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaExcepetion(
						String.format("nao existe %d", cozinhaId)));
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.saveAndFlush(restaurante);
	}
	
	

}
