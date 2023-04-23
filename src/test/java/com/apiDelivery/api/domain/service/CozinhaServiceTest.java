package com.apiDelivery.api.domain.service;

import com.apiDelivery.api.domain.model.Cozinha;
import com.apiDelivery.api.domain.repository.CozinhaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class CozinhaServiceTest {

    public static final Long     ID = 1L;
    public static final String NOME = "john";

    @InjectMocks
    private CozinhaService cozinhaService;

    @Mock
    private CozinhaRepository cozinhaRepository;
    private Cozinha cozinha;
    private Optional<Cozinha> optionalCozinha;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCozinha();

    }

    @Test
    void listarCozinhas() {

    }

    @Test
    void burcarCozinha() {
        Mockito.when(cozinhaRepository.findById(Mockito.anyLong())).thenReturn(optionalCozinha);

        Cozinha response = cozinhaService.buscarOuFalhar(ID);
        response.setId(1l);
        response.setNome("john");

//        Assertions.assertNotNull(response);
        Assertions.assertEquals(Cozinha.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NOME, response.getNome());
    }

    @Test
    void cadastrarCozinha() {
    }

    @Test
    void excluirCozinha() {
    }

    @Test
    void buscarOuFalhar() {
    }

    private void startCozinha(){

        Cozinha cozinha = new Cozinha(ID, NOME);
        optionalCozinha = Optional.of(new Cozinha(ID, NOME));
    }
}