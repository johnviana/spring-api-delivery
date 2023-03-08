package com.apiDelivery.api.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.apiDelivery.api.domain.exception.EntidadeEmUsoException;
import com.apiDelivery.api.domain.exception.EntidadeNaoEncontradaExcepetion;
import com.apiDelivery.api.domain.model.Cidade;
import com.apiDelivery.api.domain.model.Cozinha;
import com.apiDelivery.api.domain.model.FormaPagamento;
import com.apiDelivery.api.domain.model.Restaurante;
import com.apiDelivery.api.domain.repository.CozinhaRepository;
import com.apiDelivery.api.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {
	
	@Autowired
	RestauranteRepository restauranteRepository;
	
	@Autowired
	CozinhaRepository cozinhaRespository;
	
	@Autowired
	CozinhaService cozinhaService;
	
	@Autowired
	CidadeService cidadeService;
	
	@Autowired
	FormaPagamentoService formaPagamentoService;
	
	
	public List<Restaurante> listarRestuarantes(){
		return restauranteRepository.findAll();
	}
	
	@Transactional
	public Restaurante adicionar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId); 
		
		Long cidadeId = restaurante.getEndereco().getCidade().getId();
		Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);
		
		
		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);
				
		
		return restauranteRepository.save(restaurante);
	}
	
	
	@Transactional
	public void excluirRestaurante(Long id) {
		try {
			restauranteRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExcepetion(
					String.format("Codigo %d do restaurante não encontrado ", id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Entidade em uso náo pode ser excluido ", id));
		}
	}
	
	@Transactional
	public void ativar(Long id) {
		Restaurante restauranteAtual = buscarOuFalhar(id);
		
//		restauranteAtual.setAtivo(true);
		restauranteAtual.ativar();
	}
	
	@Transactional
	public void inativar(Long id) {
		Restaurante restauranteAtual = buscarOuFalhar(id);	
		
//		restauranteAtual.setAtivo(false);
		restauranteAtual.inativar();
	}
	
	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(()-> new EntidadeNaoEncontradaExcepetion(
						String.format("Restaurante não encontrado %d ", restauranteId)));
	}
	
	
	/*
	 * dessociar as forma de pagamento
	 */
	
	@Transactional
	public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		
		FormaPagamento formaPagamento = formaPagamentoService.bucarOuFalhar(formaPagamentoId);
		restaurante.desassociarFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		
		FormaPagamento formaPagamento = formaPagamentoService.bucarOuFalhar(formaPagamentoId);
		restaurante.associarFormaPagamento(formaPagamento);
	}
	
	

}
