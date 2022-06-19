package com.buscador.cep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CepApplication {

	public static void main(String[] args) {
		SpringApplication.run(CepApplication.class, args);
	}

}
