package com.apiDelivery.api.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiDelivery.api.domain.service.FluxoPedidoService;

@RestController
@RequestMapping(value = "/pedido/{codigoId}")
public class FluxoPedidoController {
	
	
	@Autowired
	FluxoPedidoService fluxoPedidoService;
	
	@PutMapping("/confirmacao")
	public void confirmar(@PathVariable String codigoId) {
		fluxoPedidoService.confirmar(codigoId);
	}

}
