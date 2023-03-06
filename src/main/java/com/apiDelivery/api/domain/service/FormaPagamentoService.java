package com.apiDelivery.api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiDelivery.api.domain.model.FormaPagamento;
import com.apiDelivery.api.domain.repository.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	
	// listar todos as forma de pagamentos
	
	public List<FormaPagamento> listarTodos(){
		return formaPagamentoRepository.findAll();
	}
}
