package com.apiDelivery.api.domain.repository;


import com.apiDelivery.api.domain.model.EmailServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailServerRepository extends JpaRepository<EmailServer, Integer> {
}
