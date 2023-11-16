package com.apiDelivery.api.domain.model;



import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Data
@Table(name = "permissao")
public class Permissao implements GrantedAuthority, Serializable {


	public Permissao(){}

	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column
	private Long id;

	@Column
	private String nome;
	
	@Column
	private String descricao;

	@Override
	public String getAuthority() {
		return this.descricao;
	}
}


