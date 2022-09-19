package com.itau.escolaItauSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableJpaAuditing
@SpringBootApplication
@EnableFeignClients
public class EscolaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(EscolaSpringApplication.class, args);
	}

}



