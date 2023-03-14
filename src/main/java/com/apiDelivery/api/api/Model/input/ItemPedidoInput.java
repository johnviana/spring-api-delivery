package com.apiDelivery.api.api.Model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInput {
	
	private Long produtoId;
	private Integer quantidade;
	private String observacao;

}
