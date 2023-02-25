package com.apiDelivery.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.apiDelivery.domain.exception.EntidadeEmUsoException;
import com.apiDelivery.domain.exception.EntidadeNaoEncontradaExcepetion;
import com.apiDelivery.domain.exception.EstadoNaoEncontradaExcepetion;
import com.apiDelivery.domain.model.Estado;
import com.apiDelivery.domain.repository.EstadoRepository;

@Service
public class EstadoService {
	
	private static final String ENTIDADE_ESTADO_EM_USO = "Entidade de código %d esta em uso ";
	private static final String CODIGO_DE_ESTADO_NÃO_ENCONTRADO = "Codigo de Estado não encontrada %d ";
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	
	public List<Estado> buscarTodosEstado() {
		return estadoRepository.findAll();
		
	}
	
	public Estado buscarOuFalhar(Long id) {
		return estadoRepository.findById(id)
				.orElseThrow(() -> new EstadoNaoEncontradaExcepetion(
						String.format(CODIGO_DE_ESTADO_NÃO_ENCONTRADO, id)));
	}
	
	
	public Estado salvarEstado(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public void excluirEstado(Long id){
		
		try {
			estadoRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradaExcepetion(
					String.format(CODIGO_DE_ESTADO_NÃO_ENCONTRADO, id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(ENTIDADE_ESTADO_EM_USO, id));
		}
		
		
	}
}
