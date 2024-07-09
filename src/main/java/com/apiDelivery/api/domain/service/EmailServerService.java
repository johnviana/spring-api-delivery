package com.apiDelivery.api.domain.service;

import com.apiDelivery.api.domain.exception.EntidadeEmUsoException;
import com.apiDelivery.api.domain.exception.EntidadeNaoEncontradaExcepetion;
import com.apiDelivery.api.domain.model.EmailServer;
import com.apiDelivery.api.domain.model.dto.EmailServerDTO;
import com.apiDelivery.api.domain.repository.EmailServerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServerService {

    private static final String CODIGO_DE_SERVIDOR_EMAIL_ENCONTRADO = "Codigo do Servidor Email não encontrada %d ";

    @Autowired
    private EmailServerRepository emailServerRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public EmailServerDTO buscarEmailServe(Integer codigo){
      EmailServer emailServer = emailServerRepository.findById(codigo)
              .orElseThrow(() -> new EntidadeNaoEncontradaExcepetion(
                      String.format(CODIGO_DE_SERVIDOR_EMAIL_ENCONTRADO, codigo)) {
              });
      return new EmailServerDTO(emailServer);
    }
    
    @Transactional
    public EmailServerDTO salvarEmailServer(EmailServerDTO emailServerDTO) {
        try {
            EmailServer emailServer = emailServerDTO.toEntity();
            EmailServer savedEmailServer = emailServerRepository.save(emailServer);
            return new EmailServerDTO(savedEmailServer);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o servidor de e-mail", e);
        }
    }

    @Transactional
    public EmailServerDTO atualizarEmailServer(Integer codigo, EmailServerDTO emailServerDTO) {
        try {
            EmailServer existingEmailServer = emailServerRepository.findById(codigo)
                    .orElseThrow(() -> new EntidadeEmUsoException.ServidorEmailNaoEncontradaExcepetion(String.format(CODIGO_DE_SERVIDOR_EMAIL_ENCONTRADO, codigo)));

            existingEmailServer.atualizarEntityParaDTO(emailServerDTO);
            EmailServer updatedEmailServer = emailServerRepository.save(existingEmailServer);

            return new EmailServerDTO(updatedEmailServer);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o servidor de e-mail", e);
        }
    }


}