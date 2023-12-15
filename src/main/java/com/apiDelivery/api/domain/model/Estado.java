package com.apiDelivery.api.domain.model;


import com.apiDelivery.api.core.validation.Groups;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Data
public class Estado extends RepresentationModel<Estado>{
	
	@NotNull(groups = Groups.EstadoId.class)
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@NotBlank
	@Column
	private String nome;

}
