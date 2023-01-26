package com.apispring.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Data
@Table(name ="estado")
public class Estado {
	
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "id_estado")
	private Long idEstado;
	
	@Column
	private String nome;

}
