package com.oriontek.oriontek.customers.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.oriontek.oriontek.customers.app.infrastructure.persistence.entity")
@EnableJpaRepositories(
   basePackages = {
        "com.oriontek.oriontek.customers.app.infrastructure.persistence.repositories.customer",
        "com.oriontek.oriontek.customers.app.infrastructure.persistence.repositories.address"
    }
)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
