package com.apiDelivery.api.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apiDelivery.api.domain.service.EnvioEmailService;
import com.apiDelivery.api.infrastructure.service.FakeEnvioEmailService;
import com.apiDelivery.api.infrastructure.service.SandboxEnvioEmailService;
import com.apiDelivery.api.infrastructure.service.SmtpEnvioEmailService;

@Configuration 
public class EmailConfig {
	
	@Autowired
	EmailProperties emailProprerties;
	
	@Bean
	public EnvioEmailService envioEmailService() {
		
		switch(emailProprerties.getImpl()) {
		case FAKE:
			return new FakeEnvioEmailService();
		case SMTP:
			return new SmtpEnvioEmailService();
		case SANDBOX:
			return new SandboxEnvioEmailService();
			default:
				return null;
		}
	}

}
