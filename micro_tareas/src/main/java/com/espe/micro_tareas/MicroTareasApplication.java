package com.espe.micro_tareas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroTareasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroTareasApplication.class, args);
	}

}
