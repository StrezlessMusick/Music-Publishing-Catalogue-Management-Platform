package com.formula38.appuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan()
//@EnableJpaRepositories()
//@EntityScan()
public class AppuserApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppuserApplication.class, args);
	}

}
