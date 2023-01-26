package com.apispring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apispring.domain.model.Produto;

@Repository
public interface ProdutoRespository extends JpaRepository<Produto, Long> {

}
