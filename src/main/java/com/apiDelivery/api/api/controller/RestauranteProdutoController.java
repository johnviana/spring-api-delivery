package com.apiDelivery.api.api.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiDelivery.api.api.Model.ProdutoModel;
import com.apiDelivery.api.api.Model.input.ProdutoInput;
import com.apiDelivery.api.api.assembler.DisassemblerModel.ProdutoInputDisassembler;
import com.apiDelivery.api.api.assembler.assemblerModel.ProdutoModelAssembler;
import com.apiDelivery.api.domain.model.Produto;
import com.apiDelivery.api.domain.model.Restaurante;
import com.apiDelivery.api.domain.repository.ProdutoRepository;
import com.apiDelivery.api.domain.service.ProdutoService;
import com.apiDelivery.api.domain.service.RestauranteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;
	
	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private ProdutoRepository produtorepository;
	
	@GetMapping
	public List<ProdutoModel> listar(@PathVariable Long restauranteId){
		
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		
		List<Produto> todosProdutos = produtorepository.findByRestaurante(restaurante);
		
		return produtoModelAssembler.toCollectionModel(todosProdutos);
		
		
	}
	
	@PostMapping
	public ProdutoModel adicionar(@PathVariable Long restauranteId, 
			@RequestBody @Valid ProdutoInput produtoInput) {
		
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		
		Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
		produto.setRestaurante(restaurante);
		
		produto = produtoService.salvar(produto);
		
		return produtoModelAssembler.toModel(produto);
	}
	
	@PutMapping("/{produtoId}")
	public ProdutoModel atualizar(@RequestBody ProdutoInput produtoInput, 
			@PathVariable Long produtoId, @Valid @PathVariable Long restauranteId) {
		
		Produto produtoAtual = produtoService.buscarOuFalhar(restauranteId, produtoId);
		
		produtoInputDisassembler.copyToDomianObject(produtoInput, produtoAtual);
		
		produtoAtual = produtoService.salvar(produtoAtual);
		
		return produtoModelAssembler.toModel(produtoAtual);
	}

}
