package com.apiDelivery.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.apiDelivery") // Especifique o pacote raiz onde os componentes devem ser escaneados
public class SpringApiDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringApiDeliveryApplication.class, args);
		

	}
	
	


}
