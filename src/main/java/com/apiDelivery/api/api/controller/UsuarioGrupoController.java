package com.apiDelivery.api.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.apiDelivery.api.api.Model.GrupoModel;
import com.apiDelivery.api.api.assembler.assemblerModel.GrupoModelAssembler;
import com.apiDelivery.api.domain.model.Usuario;
import com.apiDelivery.api.domain.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@GetMapping
	public List<GrupoModel> listar(@PathVariable Long usuarioId){
		Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
		return grupoModelAssembler.toCollectionModel(usuario.getGrupos());
	}
	
	@PutMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioService.associar(usuarioId, grupoId);
	}
	
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioService.desassociar(usuarioId, grupoId);
	}

}
