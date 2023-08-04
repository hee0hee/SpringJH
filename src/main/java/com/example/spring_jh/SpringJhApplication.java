package com.example.spring_jh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringJhApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJhApplication.class, args);
	}

}
