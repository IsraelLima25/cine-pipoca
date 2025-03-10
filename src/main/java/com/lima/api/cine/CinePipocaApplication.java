package com.lima.api.cine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// TODO: Aplicar um formatador/lint exemplo checkstyle ou spotless
// TODO: O projeto utiliza bastante manutenção de estados. Check a possibilidade de aplicar o pattern GOF state

@SpringBootApplication
@EnableScheduling
public class CinePipocaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinePipocaApplication.class, args);
	}

}
