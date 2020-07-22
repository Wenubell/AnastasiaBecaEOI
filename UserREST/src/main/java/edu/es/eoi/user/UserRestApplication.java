package edu.es.eoi.user;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRestApplication.class, args);
	}
	
	@Bean
	public EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("REST_PERSISTENCE_UNIT").createEntityManager();
	}

}
