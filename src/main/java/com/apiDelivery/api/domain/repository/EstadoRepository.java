package com.apiDelivery.api.domain.repository;

import com.apiDelivery.api.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

}
