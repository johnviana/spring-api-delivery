package com.apiDelivery.api.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.apiDelivery.api.api.Model.FormaPagamentoModel;
import com.apiDelivery.api.api.Model.input.FormaPagamentoInput;
import com.apiDelivery.api.api.assembler.FormaPagamantoModelAssembler;
import com.apiDelivery.api.api.assembler.FormaPagamentoInputDisassembler;
import com.apiDelivery.api.domain.exception.EntidadeNaoEncontradaExcepetion;
import com.apiDelivery.api.domain.exception.NegocioException;
import com.apiDelivery.api.domain.model.FormaPagamento;
import com.apiDelivery.api.domain.service.FormaPagamentoService;

@RestController
@RequestMapping(value = "/api/formapagamentos")
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private FormaPagamentoInputDisassembler formaDisassemble;
	
	@Autowired
	private FormaPagamantoModelAssembler formaPagamentoModelAssemler;
	
	/*
	 * Metodo Listar todos
	 */

	@GetMapping
	public List<FormaPagamento> listar(){
		return formaPagamentoService.listarTodos();
	
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoModel salvar(@RequestBody FormaPagamentoInput formaPagamentoInput) {
		
		FormaPagamento forma = formaDisassemble.toDomainObject(formaPagamentoInput);

		try {
			return formaPagamentoModelAssemler.toModel(formaPagamentoService.salvar(forma));

		} catch (EntidadeNaoEncontradaExcepetion e) {
			throw new NegocioException(e.getMessage());
		}
		
	}
	
	@PutMapping("/{id}")
	public FormaPagamento atualizar(@PathVariable Long id, 
			@RequestBody FormaPagamento formaPagamento) {
		
		FormaPagamento formapg = formaPagamentoService.bucarOuFalhar(id);
		
		BeanUtils.copyProperties(formaPagamento, formapg, "id");
		return formaPagamentoService.salvar(formapg);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		formaPagamentoService.excluir(id);
	}
	
}
