package com.apiDelivery.api.domain.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Data
public class FormaPagamento {
	
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Column(nullable = false)
	private String descricao;

}
