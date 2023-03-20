package com.apiDelivery.api.domain.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiDelivery.api.domain.exception.NegocioException;
import com.apiDelivery.api.domain.model.Pedido;
import com.apiDelivery.api.domain.model.StatusPedido;
import com.apiDelivery.api.domain.service.EnvioEmailService.Mensagem;

@Service
public class FluxoPedidoService {
	
	@Autowired
	private EmissaoPedidoService emissaoPedidoService;

	@Autowired
	private EnvioEmailService envioEmail;
	
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
		
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
				.corpo("o Pedido de código" + pedido.getCodigo() + " foi Confirmado")
				.destinatario(pedido.getCliente().getEmail())
				.destinatario("johnvianaluz@hotmail.com")
				.build();	
		
		envioEmail.enviar(mensagem);
				
	}
	
	@Transactional
	public void cancelarPedido(String codigoId) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoId);
		
		if(!pedido.getStatus().equals(StatusPedido.CRIADO)) {
			throw new NegocioException(
					String.format("Status de pedido %s não pode ser cancelado de %s para %s ",
						pedido.getCodigo(),
						pedido.getStatus().getDescricao(),
						StatusPedido.CANCELADO.getDescricao()));
		}
		pedido.setStatus(StatusPedido.CANCELADO);
		pedido.setDataCancelamento(OffsetDateTime.now());
	}
	
	public void entregar(String codigoId) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoId);
		
		if(!pedido.getStatus().equals(StatusPedido.CONFIRMADO)) {
			throw new NegocioException(
					String.format("Status do pedido %s não pode ser entregue de %s de %s",
					pedido.getCodigo(),
					pedido.getStatus().getDescricao(),
					StatusPedido.CONFIRMADO.getDescricao()));
		}
		
		pedido.setStatus(StatusPedido.ENTREGUE);
		pedido.setDataEntrega(OffsetDateTime.now());
	}

}
