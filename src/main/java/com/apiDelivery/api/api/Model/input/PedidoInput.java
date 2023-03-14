package com.apiDelivery.api.api.Model.input;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInput {
	
	private RestauranteIdInput restaurante;
	private EnderecoInput enderecoEntrega;
	private FormaPagamentoIdInput formaPagamento;
	private List<ItemPedidoInput> itens;
	

}
