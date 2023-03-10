package com.apiDelivery.api.api.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

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

import com.apiDelivery.api.api.Model.RestauranteModel;
import com.apiDelivery.api.api.Model.input.RestauranteInput;
import com.apiDelivery.api.api.assembler.DisassemblerModel.RestauranteInputDisassembler;
import com.apiDelivery.api.api.assembler.assemblerModel.RestauranteModelAssembler;
import com.apiDelivery.api.domain.exception.EntidadeNaoEncontradaExcepetion;
import com.apiDelivery.api.domain.exception.NegocioException;
import com.apiDelivery.api.domain.model.Restaurante;
import com.apiDelivery.api.domain.repository.RestauranteRepository;
import com.apiDelivery.api.domain.service.RestauranteService;
import com.apiDelivery.api.infrastructure.spec.RestauranteSpecs;

@RestController
@RequestMapping(value = "api/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;
	
	@Autowired
	private RestauranteInputDisassembler restauranteInputDisassembler;
	
	@Autowired
	private RestauranteService restauranteService;

	@Autowired
	private RestauranteRepository restauranteRepository;

	@GetMapping
	public List<RestauranteModel> listarTodosRestaurantes() {
		return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
	}

	@GetMapping("/{restauranteId}")
	public RestauranteModel buscarUmRestaurante(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

		return restauranteModelAssembler.toModel(restaurante);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteModel salvar(@RequestBody @Valid RestauranteInput restauranteInput) {

		Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);

		try {
			return restauranteModelAssembler.toModel(restauranteService.adicionar(restaurante));

		} catch (EntidadeNaoEncontradaExcepetion e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@PutMapping("/{id}")
	public RestauranteModel atualizar(@PathVariable @Valid Long id, @RequestBody RestauranteInput restauranteInput) {

		try {
			
//		Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
		Restaurante restauranteAtual = restauranteService.buscarOuFalhar(id);
		
		restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);
				
		
		
//		BeanUtils.copyProperties(restaurante, restauranteAtual, 
//				"id", "formasPagamento","dataCadastro", "endereco", "produtos");
		
			return restauranteModelAssembler.toModel(restauranteService.adicionar(restauranteAtual));

		} catch (EntidadeNaoEncontradaExcepetion e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		restauranteService.excluirRestaurante(id);
	}

	@GetMapping("/taxa")
	public List<Restaurante> buscarPorFrete(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.find(nome, taxaInicial, taxaFinal);
	}

	@GetMapping("/nomeid")
	public List<Restaurante> buscarNomeIdCozinha(String nome, Long cozinhaId) {
		return restauranteRepository.buscarPorNomeIdCozinha(nome, cozinhaId);
	}

	@GetMapping("/taxazero")
	public List<Restaurante> buscarComFreteGratesSpec(String nome) {
//		var comFreteGratis = new RestauranteComFreteGratisSpec();
//		var comNomeIgual = new RestauranteComNomeSemelhantesSpec(nome);

		return restauranteRepository
				.findAll(RestauranteSpecs.comFreteGratis().and(RestauranteSpecs.comNomeSemelhantes(nome)));

	}
	
	@PutMapping("/{id}/ativar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long id){
		restauranteService.ativar(id);
		
	}
	
	@DeleteMapping("/{id}/ativar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long id) {
		restauranteService.inativar(id);
	}
	
	@PutMapping("/{restauranteId}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abertura(@PathVariable Long restauranteId) {
		restauranteService.abrir(restauranteId);
	}
	
	@PutMapping("/{restauranteId}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void fechamento(@PathVariable Long restauranteId) {
		restauranteService.fechar(restauranteId);
	}


}
