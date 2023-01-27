package com.apispring.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apispring.domain.model.Cozinha;
import com.apispring.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {

	@Autowired
	CozinhaRepository cozinhaRepository;
	
	public List<Cozinha> listarCozinhas(){
		return cozinhaRepository.findAll();
	}
	
	public Optional<Cozinha> burcarCozinha(Long id) {
	 return cozinhaRepository.findById(id);
	}
	
	public Cozinha cadastrarCozinha(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public void excluirCozinha(Cozinha cozinha) {
	  cozinhaRepository.deleteById(cozinha.getIdCozinha());
	}
}
