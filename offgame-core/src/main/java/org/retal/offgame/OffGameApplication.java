package org.retal.offgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OffGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(OffGameApplication.class, args);
	}

}
