package com.apiDelivery.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiDelivery.api.domain.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

}
