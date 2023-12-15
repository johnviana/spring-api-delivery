package com.apiDelivery.api.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {
	
	@Column
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String codigo;
	
	@Column
	private BigDecimal subtotal;
	
	@Column
	private BigDecimal taxaFrete;
	
	@Column
	private BigDecimal valorTotal;
	
	@Embedded
	private Endereco enderecoEntrega;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status = StatusPedido.CRIADO;
	
	@CreationTimestamp
	private OffsetDateTime dataCriacao;
	
	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataEntrega;

	@ManyToOne
	@JoinColumn()
	private FormaPagamento formaPagamento;
	
	@ManyToOne
	@JoinColumn()
	private Restaurante restaurante;
	
	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id")
	private Usuario cliente;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();
	
	
	public void calcularValorTotal() {
	    getItens().forEach(ItemPedido::calcularPrecoTotal);
	    
	    this.subtotal = getItens().stream()
	        .map(item -> item.getPrecoTotal())
	        .reduce(BigDecimal.ZERO, BigDecimal::add);
	    
	    this.valorTotal = this.subtotal.add(this.taxaFrete);
	}
	
	public void definirFrete() {
		setTaxaFrete(getRestaurante().getTaxaFrete());
	}
	
	@PrePersist
	private void gerarCodigo() {
		setCodigo(UUID.randomUUID().toString());
	}
	

}
