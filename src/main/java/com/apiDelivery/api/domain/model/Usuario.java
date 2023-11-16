package com.apiDelivery.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author johnv
 */

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "usuarios")
public class Usuario implements UserDetails, Serializable {

	public Usuario(){}

	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column
	private Long id;

	@Column(nullable = false, unique = true)
	private String nome;

	@Column(nullable = false)
	private String email;


	@Column(nullable = false)
	private String senha;

	private Boolean accountNonExpired;
	private Boolean accountNonLocked;
	private Boolean credentialsNonExpired;
	private Boolean enable;



	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;


	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_grupo",
			 joinColumns = {@JoinColumn(name = "usuario_id",
			referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "grupo_id",
			referencedColumnName = "id")})
	private List<Grupo> grupos;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_permissao",
			joinColumns = {@JoinColumn(name = "usuario_id",
					referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "permissao_id",
					referencedColumnName = "id")})
	private List<Permissao> permissoes;


	public List<String> getRoles(){
		List<String> roles = new ArrayList<>();
		for (Permissao permissao : permissoes){
			roles.add(permissao.getDescricao());
		}
		return roles;
	}

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissoes;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.nome;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enable;
	}
}
