package com.apiDelivery.api.domain.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiDelivery.api.domain.exception.NegocioException;
import com.apiDelivery.api.domain.model.Pedido;
import com.apiDelivery.api.domain.model.StatusPedido;

@Service
public class FluxoPedidoService {
	
	@Autowired
	EmissaoPedidoService emissaoPedidoService;
	
	@Transactional
	public void confirmar(String codigoId ) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoId);
		
		if(!pedido.getStatus().equals(StatusPedido.CRIADO)) {
			throw new NegocioException(
					String.format("Status do pedido %s não pode ser alterado de %s para %s", 
							pedido.getCodigo(), 
							pedido.getStatus().getDescricao(), 
							StatusPedido.CONFIRMADO.getDescricao()));
		}
		pedido.setStatus(StatusPedido.CONFIRMADO);
		pedido.setDataConfirmacao(OffsetDateTime.now());
	}

}
