package com.apiDelivery.api.core.validation;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {
	
	public LocalValidatorFactoryBean validator(MessageSource messageSoucer) {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSoucer);
		return bean;
	}

}
