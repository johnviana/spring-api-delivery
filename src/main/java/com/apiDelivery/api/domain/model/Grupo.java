package com.apiDelivery.api.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
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
			joinColumns = {@JoinColumn(name= "grupo_id",
					referencedColumnName = "id")},
			inverseJoinColumns = @JoinColumn(name = "permissao_id",
			referencedColumnName = "id"))
	private Set<Permissao> permissoes = new HashSet<>();

	
	public boolean removerPermissoes(Permissao permissao) {
		return getPermissoes().remove(permissao);
	}
	
	public boolean adicionarPermissao(Permissao permissao) {
		return getPermissoes().add(permissao);
	}
	

}
