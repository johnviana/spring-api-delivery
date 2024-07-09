package com.apiDelivery.api.assembler.DisassemblerModel;

import com.apiDelivery.api.Model.input.UsuarioInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apiDelivery.api.domain.model.Usuario;

@Component
public class UsuarioModelDisassembler {

	@Autowired
	private ModelMapper modelMapper;
		
	public Usuario toDomainObject(UsuarioInput usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}
	
	public void copyToDomainObject(UsuarioInput usuarioInput, Usuario usuario) {
		modelMapper.map(usuarioInput, usuario);
	}
	
}
