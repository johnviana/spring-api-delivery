package com.apiDelivery.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiDelivery.api.domain.model.Usuario;

@Repository
public interface UsuarioRespository extends JpaRepository<Usuario, Long>{

}
