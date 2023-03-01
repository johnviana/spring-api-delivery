package com.apiDelivery.api.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.apiDelivery.api.domain.exception.CidadeNaoEncontradaExcepetion;
import com.apiDelivery.api.domain.exception.EntidadeEmUsoException;
import com.apiDelivery.api.domain.exception.EntidadeNaoEncontradaExcepetion;
import com.apiDelivery.api.domain.model.Cidade;
import com.apiDelivery.api.domain.model.Estado;
import com.apiDelivery.api.domain.repository.CidadeRepository;
import com.apiDelivery.api.domain.repository.EstadoRepository;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoService estadoService;
	
		
	public List<Cidade> listarCidades(){
		return cidadeRepository.findAll();
	}
	
	
	public Cidade buscarOuFalhar(Long id) {
		return cidadeRepository.findById(id)
				.orElseThrow(() -> new CidadeNaoEncontradaExcepetion(
						String.format("Codigo de Cidade %d não existe ", id)));
	}
	
	@Transactional
	public Cidade salvarCidade(Cidade cidade) {
		
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoService.buscarOuFalhar(estadoId);
		
		
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
		
	}
	
	@Transactional
	public void excluirCidade(Long id) {
		try {
			
			cidadeRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaExcepetion(
					String.format("Entidade de condigo %d não existe ", id)); 
			
		} catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Entidade de codigo %d em uso", id));
		}
	}
		
}
