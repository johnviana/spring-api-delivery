package com.apiDelivery.api.api.controller;

import com.apiDelivery.api.domain.exception.EntidadeNaoEncontradaExcepetion;
import com.apiDelivery.api.domain.exception.NegocioException;
import com.apiDelivery.api.domain.model.Cidade;
import com.apiDelivery.api.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping
	public List<Cidade> listarTodasCidades(){
		return cidadeService.listarCidades();
	}
	
	@GetMapping("/{id}")
	public Cidade buscarCidade(@PathVariable Long id) {
		return cidadeService.buscarOuFalhar(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade salvar(@RequestBody @Valid Cidade cidade) {
		try {
			return cidadeService.salvarCidade(cidade);			
		} catch (EntidadeNaoEncontradaExcepetion e) {
			throw new NegocioException(e.getMessage());
		}
		
	}
	
	@PutMapping("/{id}")
	public Cidade atualizarCidade(@PathVariable @Valid Long id, 
			@RequestBody Cidade cidade) {
		
		Cidade cidadeNova = cidadeService.buscarOuFalhar(id);
		BeanUtils.copyProperties(cidade, cidadeNova);
		
		try {
			
			return cidadeService.salvarCidade(cidadeNova);
				
		} catch (EntidadeNaoEncontradaExcepetion e) {
			throw new NegocioException(e.getMessage());
			
		}
		
		
	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void excluirUmaCidade(@PathVariable Long id) {
		 cidadeService.excluirCidade(id);
	}

}
