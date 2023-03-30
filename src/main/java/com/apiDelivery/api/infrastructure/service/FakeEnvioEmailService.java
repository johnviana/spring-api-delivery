package com.apiDelivery.api.infrastructure.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEnvioEmailService extends SmtpEnvioEmailService{

	@Override
	public void enviar(Mensagem mensagem) {
		
		String corpo = processarTemplate(mensagem);
		
		log.info("[FAKE E-MAIL] PARA: {}\n{}", mensagem.getDestinatarios(), corpo);
	}
}
