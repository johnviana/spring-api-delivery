package com.apiDelivery.api.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apiDelivery.api.api.Model.FormaPagamentoModel;
import com.apiDelivery.api.domain.model.FormaPagamento;

@Component
public class FormaPagamantoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
		return modelMapper.map(formaPagamento, FormaPagamentoModel.class); 
	}
	

	public List<FormaPagamentoModel> toCollectionModel(List<FormaPagamento> formaPagamentos) {
		return formaPagamentos.stream()
				.map(formaPagamento -> toModel(formaPagamento)).collect(Collectors.toList());
	}

	
}
