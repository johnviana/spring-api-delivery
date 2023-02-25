package com.apiDelivery.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiDelivery.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{
	
	List<Cozinha> nome(String nome);
	
	Optional<Cozinha> findByNome(String nome);
	
	List<Cozinha> findTodosByNomeContaining(String nome);

}
