package com.apiDelivery.api.api.Model.input;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeIdInput {
	
	@NotNull
	private Long id;

}
