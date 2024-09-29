package com.kamila.backofficeteste.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.kamila.backofficeteste.services.DBService;

//classe de confuguração tem que fazer esse import -> olhar no application.properties, pois o perfil é o de teste
@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	
	@Bean
	public void instanciaDB() {
		this.dbService.instanciaDB();
	}

}
