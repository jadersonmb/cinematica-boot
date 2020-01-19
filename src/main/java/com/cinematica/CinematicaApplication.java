package com.cinematica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.cinematica.*" })
public class CinematicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinematicaApplication.class, args);
	}
}
