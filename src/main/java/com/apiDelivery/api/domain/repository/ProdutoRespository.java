package com.apiDelivery.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiDelivery.api.domain.model.Produto;

@Repository
public interface ProdutoRespository extends JpaRepository<Produto, Long> {

}
