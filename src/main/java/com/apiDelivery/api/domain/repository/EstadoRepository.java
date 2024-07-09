package com.apiDelivery.api.domain.repository;

import com.apiDelivery.api.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

    @Modifying
    @Query("DELETE FROM Estado e WHERE e.id = 4")
    void deleteEstadoByIdFour();

    @Query("DELETE FROM Estado")
    void deleteAllCustom();

}
