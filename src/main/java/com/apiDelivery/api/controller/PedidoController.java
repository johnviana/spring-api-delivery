package com.apiDelivery.api.controller;

import com.apiDelivery.api.Model.PedidoModel;
import com.apiDelivery.api.Model.input.PedidoInput;
import com.apiDelivery.api.assembler.DisassemblerModel.PedidoInputDisassembler;
import com.apiDelivery.api.assembler.assemblerModel.PedidoModelAssembler;
import com.apiDelivery.api.domain.model.Pedido;
import com.apiDelivery.api.domain.model.Usuario;
import com.apiDelivery.api.domain.repository.PedidoRepository;
import com.apiDelivery.api.domain.service.EmissaoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	public PedidoModel buscar(@PathVariable String codigoId) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoId);
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
