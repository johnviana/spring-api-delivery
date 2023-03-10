package com.apiDelivery.api.api.controller;

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

import com.apiDelivery.api.api.Model.GrupoModel;
import com.apiDelivery.api.api.Model.input.GrupoInput;
import com.apiDelivery.api.api.assembler.DisassemblerModel.GrupoModelDisassembler;
import com.apiDelivery.api.api.assembler.assemblerModel.GrupoModelAssembler;
import com.apiDelivery.api.domain.model.Grupo;
import com.apiDelivery.api.domain.service.GrupoService;

@RestController
@RequestMapping(value = "/api/grupos")
public class GrupoController {

	@Autowired
	private GrupoService grupoService;

	@Autowired
	private GrupoModelAssembler grupoAssembler;

	@Autowired
	private GrupoModelDisassembler grupoDisassembler;

	@GetMapping
	public List<GrupoModel> listar() {
		List<Grupo> todosGrupos = grupoService.listarTodos();
		return grupoAssembler.toCollectionModel(todosGrupos);

	}

	@GetMapping("/{id}")
	public GrupoModel buscarGrupo(@PathVariable Long id) {

		Grupo grupoId = grupoService.buscarOuFalhar(id);
		return grupoAssembler.toModel(grupoId);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModel salvarGrupo(@RequestBody GrupoInput grupoInput) {
		Grupo grupo = grupoDisassembler.toDomainObject(grupoInput);
		grupo = grupoService.salvar(grupo);

		return grupoAssembler.toModel(grupo);
	}

	@PutMapping("/{grupoId}")
	public GrupoModel atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {

		Grupo grupoAtual = grupoService.buscarOuFalhar(grupoId);

		grupoDisassembler.copyToDomainObject(grupoInput, grupoAtual);

		grupoAtual = grupoService.salvar(grupoAtual);

		return grupoAssembler.toModel(grupoAtual);
	}

	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long grupoId) {
		grupoService.excluir(grupoId);
	}

}
