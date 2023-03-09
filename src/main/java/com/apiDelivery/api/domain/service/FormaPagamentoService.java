package com.apiDelivery.api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apiDelivery.api.domain.exception.EntidadeEmUsoException;
import com.apiDelivery.api.domain.exception.EntidadeNaoEncontradaExcepetion;
import com.apiDelivery.api.domain.model.FormaPagamento;
import com.apiDelivery.api.domain.repository.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	/*
	 *  listar todos as forma de pagamentos
	 */
	
	public List<FormaPagamento> listarTodos(){
		return formaPagamentoRepository.findAll();
	}
	
	
	/*
	 *  Salvar uma forma de pagamentos
	 */
	
	@Transactional
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save(formaPagamento);
	}
	
	@Transactional
	public void excluir(Long id) {
		try {			
			formaPagamentoRepository.deleteById(id);
			formaPagamentoRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new  EntidadeNaoEncontradaExcepetion(
					String.format("Forma de Pagamento não encontrada", id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Entidade em uso não pode ser excluida", id));
		}
	}
	
	public FormaPagamento bucarOuFalhar(Long id) {
		return formaPagamentoRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaExcepetion(
						String.format("Forma de pagamento não Encontrada", id)));
						
	}
	
}
