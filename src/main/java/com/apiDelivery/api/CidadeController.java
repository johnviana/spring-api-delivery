package com.apiDelivery.api;

import java.util.List;

import javax.validation.Valid;

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

import com.apiDelivery.domain.exception.EntidadeNaoEncontradaExcepetion;
import com.apiDelivery.domain.exception.NegocioException;
import com.apiDelivery.domain.model.Cidade;
import com.apiDelivery.domain.service.CidadeService;

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
	public Cidade atualizarCidade(@PathVariable Long id, 
			@RequestBody Cidade cidade) {
		
		Cidade cidadeNova = cidadeService.buscarOuFalhar(id);
		BeanUtils.copyProperties(cidade, cidadeNova, "id");
		
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
