package com.apiDelivery.api.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
@Table
public class CleanUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tableName; // Nome da tabela a ser limpa

    private char periodicity; // 'D' para diária, 'M' para mensal, 'S' para semanal

    private boolean active; // Indica se o job de limpeza está ativo

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastExecutionDate; // Data da última execução

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime nextExecutionDate; // Data da próxima execução
}