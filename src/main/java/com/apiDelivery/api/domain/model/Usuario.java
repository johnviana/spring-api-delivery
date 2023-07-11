package com.apiDelivery.api.domain.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author johnv
 */

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {
	
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String email;
	
	
	@Column(nullable = false)
	private String senha;
	

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;
	
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "usuario_grupo", 
			 joinColumns = {@JoinColumn(name = "usuario_id",
			referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "grupo_id",
			referencedColumnName = "id")})
	private Set<Grupo> grupos = new HashSet<>();
		
	/*
	 * Metodos para senhas
	 */
	
	public boolean senhaCoincideCom(String senha) {
	    return getSenha().equals(senha);
	}
	
	public boolean senhaNaoCoincideCom(String senha) {
		return !senhaCoincideCom(senha);
	}
	
	/*
	 * Metodos para remover grupo e adicionar grupo
	 */
	
	
	public boolean removerGrupo(Grupo grupo) {
		return getGrupos().remove(grupo);
	}
	
	public boolean adicionarGrupo(Grupo grupo) {
		return getGrupos().add(grupo);
	}

}
