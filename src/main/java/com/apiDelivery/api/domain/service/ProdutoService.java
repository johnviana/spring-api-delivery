package com.apiDelivery.api.domain.service;


import com.apiDelivery.api.domain.exception.EntidadeNaoEncontradaExcepetion;
import com.apiDelivery.api.domain.model.Produto;
import com.apiDelivery.api.domain.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
		
	}
	
	public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
		return produtoRepository.findbyId(restauranteId, produtoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaExcepetion(
						String.format("Entidade não encontrada", restauranteId, produtoId)));
				
				
	}

}
