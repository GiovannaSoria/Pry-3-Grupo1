package com.espe.micro_proyectos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroProyectosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroProyectosApplication.class, args);
	}

}
