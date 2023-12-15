package com.apiDelivery.api.domain.service;

import com.apiDelivery.api.domain.exception.EntidadeNaoEncontradaExcepetion;
import com.apiDelivery.api.domain.model.Permissao;
import com.apiDelivery.api.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository; 
	
	
	public Permissao buscarOuFalhar(Long permissaoId) {
		return permissaoRepository.findById(permissaoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaExcepetion(
						String.format("Entidade não encontrada", permissaoId)));
	}
}
