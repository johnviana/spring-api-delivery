package com.apiDelivery.api.domain.repository;

import com.apiDelivery.api.domain.model.CleanUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CleanUpRepository extends JpaRepository<CleanUp, Long> {
    // Método para encontrar limpezas ativas
    List<CleanUp> findByActiveTrue();

    CleanUp findByTableName(String tableName);

}