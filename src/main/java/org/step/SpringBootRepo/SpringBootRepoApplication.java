package org.step.SpringBootRepo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringBootRepoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRepoApplication.class, args);
	}

}
