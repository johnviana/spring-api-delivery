package com.apiDelivery.api.domain.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.apiDelivery.api.domain.exception.EntidadeEmUsoException;
import com.apiDelivery.api.domain.exception.EntidadeNaoEncontradaExcepetion;
import com.apiDelivery.api.domain.model.Cozinha;
import com.apiDelivery.api.domain.repository.CozinhaRepository;

@Service
@Component
public class CozinhaService {

	private static final String MSG_COZINHA_NAO_ENCONTRADA = 
			"Entidade não encontrada com o codigo %d";
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public List<Cozinha> listarCozinhas(){
		return cozinhaRepository.findAll();
	}
	
	public Optional<Cozinha> burcarCozinha(Long id) {
	 return cozinhaRepository.findById(id);
	}
	
	@Transactional
	public Cozinha cadastrarCozinha(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	@Transactional
	public void excluirCozinha(Long cozinhaId) {
		try {
			cozinhaRepository.deleteById(cozinhaId);
			cozinhaRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExcepetion(
					String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId)); 
		
			
		} catch(DataIntegrityViolationException e ) {
			throw new EntidadeEmUsoException(
					String.format("Cozinha em uso %d ", cozinhaId));
		}
	}
	
	public Cozinha buscarOuFalhar(Long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaExcepetion(
						String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId)));
	}
	
}
