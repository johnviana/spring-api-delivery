package com.apiDelivery.api.domain.repository;

import com.apiDelivery.api.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{
	
	List<Cozinha> nome(String nome);
	
	Optional<Cozinha> findByNome(String nome);
	
	List<Cozinha> findTodosByNomeContaining(String nome);

}
