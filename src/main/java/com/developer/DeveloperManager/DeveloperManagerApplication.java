package com.developer.DeveloperManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.developer.DeveloperManager.repository")

public class DeveloperManagerApplication {

	public static void main(String[] args) {

		SpringApplication.run(DeveloperManagerApplication.class, args);
	}

}
