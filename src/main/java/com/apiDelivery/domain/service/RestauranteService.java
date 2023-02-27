package com.apiDelivery.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.apiDelivery.domain.exception.EntidadeEmUsoException;
import com.apiDelivery.domain.exception.EntidadeNaoEncontradaExcepetion;
import com.apiDelivery.domain.model.Cozinha;
import com.apiDelivery.domain.model.Restaurante;
import com.apiDelivery.domain.repository.CozinhaRepository;
import com.apiDelivery.domain.repository.RestauranteRepository;

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
	
	@Transactional
	public Restaurante adicionar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId); 
		
//		Cozinha cozinha = cozinhaRespository.findById(cozinhaId)
//				.orElseThrow(() -> new EntidadeNaoEncontradaExcepetion(
//						String.format("não existe codigo com %d", cozinhaId)));
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	}
	
	
	@Transactional
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
