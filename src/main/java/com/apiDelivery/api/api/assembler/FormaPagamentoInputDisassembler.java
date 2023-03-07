package com.apiDelivery.api.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apiDelivery.api.api.Model.input.FormaPagamentoInput;
import com.apiDelivery.api.api.Model.input.RestauranteInput;
import com.apiDelivery.api.domain.model.Cozinha;
import com.apiDelivery.api.domain.model.FormaPagamento;
import com.apiDelivery.api.domain.model.Restaurante;

@Component
public class FormaPagamentoInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public FormaPagamento toDomainObject(FormaPagamentoInput formaPagamentoInput) {
		return modelMapper.map(formaPagamentoInput, FormaPagamento.class);
	}
	
	public void copyToDomainObject(RestauranteInput formaPagamentoInput, FormaPagamento formaPagamento) {
			
		modelMapper.map(formaPagamentoInput, formaPagamento);
	}
	

	public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
		
		restaurante.setCozinha(new Cozinha());
		
		modelMapper.map(restauranteInput, restaurante);
	}

}
