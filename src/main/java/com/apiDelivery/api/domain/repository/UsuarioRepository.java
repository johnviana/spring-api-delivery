package com.apiDelivery.api.domain.repository;

import com.apiDelivery.api.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query("SELECT u FROM Usuario WHERE u.nome =:nome")
	Usuario findBynome(String nome);

	Optional<Usuario> findByEmail(String email);


}
	