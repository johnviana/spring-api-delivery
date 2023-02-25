package com.apiDelivery.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiDelivery.domain.model.Usuario;

@Repository
public interface UsuarioRespository extends JpaRepository<Usuario, Long>{

}
