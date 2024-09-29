package com.kamila.backofficeteste.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.kamila.backofficeteste.services.DBService;

//classe de confuguração tem que fazer esse import -> olhar no application.properties, pois o perfil é o de teste
@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;
	
	
	@Bean
	 boolean instanciaDB() {
		//se o valor de spring.jpa.hibernate.ddl-auto for igual a create, significa que é para criar uma nova tabela 
		if(value.equals("create")) {
			this.dbService.instanciaDB();
		}
		return false;
	}

}
