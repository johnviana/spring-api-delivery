package com.apispring.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
	
	@Autowired
	CozinhaService cozinhaService;
	
	
	public List<Restaurante> listarRestuarantes(){
		return restauranteRepository.findAll();
	}
	

	public Restaurante adicionar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId); 
		
//		Cozinha cozinha = cozinhaRespository.findById(cozinhaId)
//				.orElseThrow(() -> new EntidadeNaoEncontradaExcepetion(
//						String.format("não existe codigo com %d", cozinhaId)));
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	}
	
	
	public void excluirRestaurante(Long id) {
		try {
			restauranteRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExcepetion(
					String.format("Codigo %d do restaurante não encontrado ", id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Entidade em uso náo pode ser excluido ", id));
		}
	}
	
	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(()-> new EntidadeNaoEncontradaExcepetion(
						String.format("Restaurante não encontrado %d ", restauranteId)));
	}
	
	
}
