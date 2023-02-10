package com.apispring.api;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.apispring.domain.model.Restaurante;
import com.apispring.domain.repository.RestauranteRepository;
import com.apispring.domain.service.RestauranteService;
import com.apispring.infrastructure.spec.RestauranteSpecs;

@RestController
@RequestMapping(value = "api/restaurantes")
public class RestauranteController {
	
	@Autowired
	RestauranteService restauranteService;
	
	@Autowired
	RestauranteRepository restauranteRepository;
	
	@GetMapping
	public List<Restaurante> listarTodosRestaurantes(){
		return restauranteRepository.findAll();
	}
	
	@GetMapping("/{restauranteId}")
	public Restaurante buscarUmRestaurante(@PathVariable Long restauranteId) {
		return restauranteService.buscarOuFalhar(restauranteId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante salvar(@RequestBody Restaurante restaurante) {
		return restauranteService.adicionar(restaurante);
		
	}
	
	@PutMapping("/{id}")
	public Restaurante atualizar(@PathVariable Long id,
			@RequestBody Restaurante restaurante){
		
			Restaurante restauranteAtual = restauranteService.buscarOuFalhar(id);
				
					BeanUtils.copyProperties(restaurante, restauranteAtual, "id",
							"formasPagamento", "endereco", "produtos");
					
					return restauranteService.adicionar(restauranteAtual);
					 
	}
	
	@GetMapping("/taxa")
	public List<Restaurante> buscarPorFrete(String nome, BigDecimal taxaInicial, 
			 BigDecimal taxaFinal){
		return restauranteRepository.find(nome, taxaInicial, taxaFinal);
	}
	
	@GetMapping("/nomeid")
	public List<Restaurante> buscarNomeIdCozinha(String nome, Long cozinhaId){
		return restauranteRepository.buscarPorNomeIdCozinha(nome, cozinhaId);
	}
	
	@GetMapping("/taxazero")
	public List<Restaurante> buscarComFreteGratesSpec(String nome){
//		var comFreteGratis = new RestauranteComFreteGratisSpec();
//		var comNomeIgual = new RestauranteComNomeSemelhantesSpec(nome);
		
		return restauranteRepository.findAll(RestauranteSpecs.comFreteGratis()
				.and(RestauranteSpecs.comNomeSemelhantes(nome)));
	
	}

}
