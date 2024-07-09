package com.apiDelivery.api.controller;

import com.apiDelivery.api.domain.exception.EntidadeEmUsoException;
import com.apiDelivery.api.domain.model.dto.EmailServerDTO;
import com.apiDelivery.api.domain.service.EmailServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/email-servers")
public class EmailServerController {

    @Autowired
    private EmailServerService emailServerService;

    @GetMapping(value = "/{codigo}")
    public EmailServerDTO buscar(@PathVariable Integer codigo) {
        return emailServerService.buscarEmailServe(codigo);

    }

    @PostMapping
    public ResponseEntity<EmailServerDTO> salva(@RequestBody EmailServerDTO emailServerDTO) {
        EmailServerDTO criarEmailServerDTO = emailServerService.salvarEmailServer(emailServerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarEmailServerDTO);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<EmailServerDTO> atualizar(@PathVariable Integer codigo, @RequestBody EmailServerDTO emailServerDTO) {
        try {
            EmailServerDTO atualizarEmailServerDTO = emailServerService.atualizarEmailServer(codigo, emailServerDTO);
            return ResponseEntity.ok(atualizarEmailServerDTO);
        } catch (EntidadeEmUsoException.ServidorEmailNaoEncontradaExcepetion e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
