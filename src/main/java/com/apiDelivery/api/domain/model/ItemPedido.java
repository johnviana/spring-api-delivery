package com.apiDelivery.api.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class ItemPedido {
	
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
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pedido pedido; 
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;
	
	public void calcularPrecoTotal() {
		BigDecimal precoUnitario = this.getPrecoUnitario();
		Integer quantidade = this.getQuantidade();

		if (precoUnitario == null) {
			precoUnitario = BigDecimal.ZERO;
		}

		if (quantidade == null) {
			quantidade = 0;
		}

		this.setPrecoTotal(precoUnitario.multiply(new BigDecimal(quantidade)));
	}
	
	

}
