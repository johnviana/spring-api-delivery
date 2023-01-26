package com.apispring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apispring.domain.model.Usuario;

@Repository
public interface UsuarioRespository extends JpaRepository<Usuario, Long>{

}
