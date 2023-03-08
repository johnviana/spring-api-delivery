package com.apiDelivery.api.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.apiDelivery.api.api.Model.UsuarioModel;
import com.apiDelivery.api.api.Model.input.SenhaInput;
import com.apiDelivery.api.api.Model.input.UsuarioInput;
import com.apiDelivery.api.api.assembler.UsuarioModelAssembler;
import com.apiDelivery.api.api.assembler.UsuarioModelSisassembler;
import com.apiDelivery.api.domain.model.Usuario;
import com.apiDelivery.api.domain.service.UsuarioService;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioModelAssembler usuarioAssembler;

	@Autowired
	private UsuarioModelSisassembler usuarioDisassembler;

	@GetMapping
	public List<UsuarioModel> listar() {
		return usuarioAssembler.toCollectionModel(usuarioService.listarTodos());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel adicionar(@RequestBody @Valid UsuarioInput usuarioInput) {
		Usuario usuario = usuarioDisassembler.toDomainObject(usuarioInput);
		usuario = usuarioService.salvar(usuario);

		return usuarioAssembler.toModel(usuario);
	}

	@PutMapping("/{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterarSenha(@PathVariable Long usuarioId,
			@RequestBody @Valid SenhaInput senha) {
		
		usuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
	}
}
