package com.apiDelivery.api.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apiDelivery.api.domain.model.Grupo;

@Component
public class GrupoModelDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	
	public Grupo toDomainObject(GrupoInput grupoInput) {
		return modelMapper.map(grupoInput, Grupo.class);
	}
	
	
	public void copyToDomainObjetc(GrupoInput grupoInput, Grupo grupo) {
		 modelMapper.map(grupoInput, grupo.getClass());
	}
}
