package com.apiDelivery.api.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiDelivery.api.api.Model.PedidoModel;
import com.apiDelivery.api.api.Model.input.PedidoInput;
import com.apiDelivery.api.api.assembler.DisassemblerModel.PedidoInputDisassembler;
import com.apiDelivery.api.api.assembler.assemblerModel.PedidoModelAssembler;
import com.apiDelivery.api.domain.model.Pedido;
import com.apiDelivery.api.domain.model.Usuario;
import com.apiDelivery.api.domain.repository.PedidoRepository;
import com.apiDelivery.api.domain.service.EmissaoPedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private EmissaoPedidoService emissaoPedidoService;
	
	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	
	@Autowired
	private PedidoInputDisassembler pedidoModelDisassembler;
	
	@GetMapping
	public List<PedidoModel> listar(){
		List<Pedido> pedidos = pedidoRepository.findAll();
		return pedidoModelAssembler.toCollectionModel(pedidos);
	}
	
	@GetMapping("/{pedidoId}")
	public PedidoModel buscar(@PathVariable Long pedidoId) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);
		return pedidoModelAssembler.toModel(pedido);
	}
	
	@PostMapping
	public PedidoModel salvar(@RequestBody PedidoInput pedidoInput) {
		
		Pedido novoPedido  = pedidoModelDisassembler.toDomainObject(pedidoInput);
		
		 // TODO pegar usuário autenticado
	        novoPedido.setCliente(new Usuario());
	        novoPedido.getCliente().setId(1L);

	        novoPedido = emissaoPedidoService.emitir(novoPedido);

	        return pedidoModelAssembler.toModel(novoPedido);
	}

}
