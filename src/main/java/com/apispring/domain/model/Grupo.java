package com.apispring.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table
public class Grupo {

	@Id
	@Column
	private Long idGrupo;
}
