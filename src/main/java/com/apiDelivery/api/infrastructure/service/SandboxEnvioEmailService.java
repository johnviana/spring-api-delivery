package com.apiDelivery.api.infrastructure.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.apiDelivery.api.core.email.EmailProperties;
import com.apiDelivery.api.domain.service.EnvioEmailService;
import com.apiDelivery.api.domain.service.EnvioEmailService.Mensagem;

public class SandboxEnvioEmailService extends SmtpEnvioEmailService{
	
	@Autowired
	private EmailProperties emailProperties;
	
	@Override
	protected MimeMessage criarMimeMessage(Mensagem mensagem) throws MessagingException {
        MimeMessage mimeMessage = super.criarMimeMessage(mensagem);
        
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setTo(emailProperties.getSandbox().getDestinatario());
        
        return mimeMessage;
        
	}
			

}
