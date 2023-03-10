package com.apiDelivery.api.domain.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Grupo {

	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@ManyToMany
	@JoinTable(name = "grupo_permissao", 
			joinColumns = @JoinColumn(name= "grupo_id"),
			inverseJoinColumns = @JoinColumn(name = "permissao_id"))
	private Set<Permissao> permissoes = new HashSet<>();

	
	public boolean removerPermissoes(Permissao permissao) {
		return getPermissoes().remove(permissao);
	}
	
	public boolean adicionarPermissao(Permissao permissao) {
		return getPermissoes().add(permissao);
	}
	

}
