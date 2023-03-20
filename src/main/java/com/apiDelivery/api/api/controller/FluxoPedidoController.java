package com.apiDelivery.api.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.apiDelivery.api.domain.service.FluxoPedidoService;

@RestController
@RequestMapping(value = "/pedido/{codigoId}")
public class FluxoPedidoController {

	@Autowired
	FluxoPedidoService fluxoPedidoService;

	@PutMapping("/confirmacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmar(@PathVariable String codigoId) {
		fluxoPedidoService.confirmar(codigoId);
	}

	@PutMapping("/entregue")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void entregue(@PathVariable String codigoId) {
		fluxoPedidoService.entregar(codigoId);
	}

	@PutMapping("/cancelamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelarPedido(@PathVariable String codigoId) {
		fluxoPedidoService.cancelarPedido(codigoId);
	}

}
