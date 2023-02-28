package com.apiDelivery.domain.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.apiDelivery.domain.exception.EntidadeEmUsoException;
import com.apiDelivery.domain.exception.EntidadeNaoEncontradaExcepetion;
import com.apiDelivery.domain.model.Cozinha;
import com.apiDelivery.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {

	private static final String MSG_COZINHA_NAO_ENCONTRADA = 
			"Entidade não encontrada com o codigo %d";
	
	@Autowired
	public CozinhaRepository cozinhaRepository;
	
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
