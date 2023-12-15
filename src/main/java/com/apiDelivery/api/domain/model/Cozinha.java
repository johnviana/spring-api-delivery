package com.apiDelivery.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cozinha extends RepresentationModel<Cozinha>{


	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column
	private String nome;

	@JsonIgnore
	@OneToMany(mappedBy = "cozinha")
	private List<Restaurante> restaurante = new ArrayList<>();

	public Cozinha(Long id, String nome) {
	}

	public Cozinha() {

	}
}
