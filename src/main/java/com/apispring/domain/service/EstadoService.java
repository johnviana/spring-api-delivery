package com.apispring.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apispring.domain.exception.EntidadeNaoEncontradaExcepetion;
import com.apispring.domain.model.Estado;
import com.apispring.domain.repository.EstadoRepository;

@Service
public class EstadoService {
	
	private static final String CODIGO_DE_ESTADO_NÃO_ENCONTRADO = "Codigo de Estado não encontrada %d ";
	@Autowired
	private EstadoRepository estadoRepository;
	
	
	public List<Estado> buscarTodosEstado() {
		return estadoRepository.findAll();
		
	}
	
	public Estado buscarOuFalhar(Long id) {
		return estadoRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaExcepetion(
						String.format(CODIGO_DE_ESTADO_NÃO_ENCONTRADO, id)));
	}
	
	public Estado salvarEstado(Estado estado) {
		return estadoRepository.save(estado);
	}

}
