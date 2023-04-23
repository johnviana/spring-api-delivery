package com.apiDelivery.api.domain.service;

import com.apiDelivery.api.domain.repository.CidadeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * create by John
 */
@SpringBootTest
class CidadeServiceTest {

    @InjectMocks
    private CidadeService cidadeService;

    @Mock
    private CidadeRepository cidadeRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarCidades() {
    }

    @Test
    void buscarOuFalhar() {
    }
    @Test
    void salvarCidade() {
    }

    @Test
    void excluirCidade() {
    }
}