package com.apiDelivery.api.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Itempedido {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private BigDecimal precoUnitario;
	
	@Column
	private BigDecimal precoTotal;
	
	@Column
	private Integer quantidade;
	
	@Column
	private String observacao;
	
	@ManyToMany
	@JoinColumn(nullable = false)
	private Pedido pedido; 
	
	@ManyToMany
	@JoinColumn(nullable = false)
	private Produto produto;
	
	

}
