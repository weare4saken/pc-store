package com.weare4saken.pcstore;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class PcStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcStoreApplication.class, args);
	}

}
