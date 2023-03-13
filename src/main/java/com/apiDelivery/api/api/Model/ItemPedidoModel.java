package com.apiDelivery.api.api.Model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoModel {
	
	 	private Long produtoId;
	    private String produtoNome;
	    private Integer quantidade;
	    private BigDecimal precoUnitario;
	    private BigDecimal precoTotal;
	    private String observacao;
	
}
