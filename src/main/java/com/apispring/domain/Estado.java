package com.apispring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name ="estado")
public class Estado {
	
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "id_estado")
	private Long idEstado;
	
	@Column
	private String nome;

}
