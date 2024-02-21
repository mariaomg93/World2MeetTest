package com.world2meettest.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class })
@OpenAPIDefinition(info = @Info(title = "Naves Espaciales API", version = "1.0", description = "Naves espaciales para test"))
@EnableJpaAuditing
@EnableCaching
public class World2MeetTestBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(World2MeetTestBackApplication.class, args);
	}

}
