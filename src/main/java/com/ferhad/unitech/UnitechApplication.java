package com.ferhad.unitech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UnitechApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnitechApplication.class, args);
	}

}
