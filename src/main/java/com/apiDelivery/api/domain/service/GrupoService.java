package com.apiDelivery.api.domain.service;

import java.util.List;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apiDelivery.api.domain.exception.EntidadeEmUsoException;
import com.apiDelivery.api.domain.exception.EntidadeNaoEncontradaExcepetion;
import com.apiDelivery.api.domain.model.Grupo;
import com.apiDelivery.api.domain.repository.GrupoRepository;

@Service
public class GrupoService {

	@Autowired
	private GrupoRepository grupoRepository;
	
	
	
	
	public List<Grupo> listarTodos(){
		return grupoRepository.findAll();
	}
	
	@Transactional
	public Grupo salvar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	
	@Transactional
	public void excluir(Long id) {
		try {
			grupoRepository.deleteById(id);
			grupoRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExcepetion(
					String.format("Codigo não encontrado do grupo", id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Id sendo usado em outra entidade", id));
			
		}
	}
	
	public Grupo buscarOuFalhar(Long id) {
		return grupoRepository.findById(id)
				.orElseThrow(()-> new EntidadeNaoEncontradaExcepetion(
						String.format("Entidade não encontrada ", id)));
	}
	
	
}
