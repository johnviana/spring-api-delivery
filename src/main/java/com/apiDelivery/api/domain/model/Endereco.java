package com.apiDelivery.api.domain.model;



import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class Endereco {
	
	@Column(name = "end_cep")
	private String cep;
	
	@Column(name = "end_logradouro")
	private String logradouro;
	
	@Column(name = "end_numero")
	private String numero;
	
	@Column(name = "end_bairro")
	private String bairro;
	
	@Column(name = "end_complemento")
	private String complemento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "end_cidade_id")
	private Cidade cidade;
	

}
