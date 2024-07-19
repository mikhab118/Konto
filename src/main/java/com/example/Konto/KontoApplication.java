package com.example.Konto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.Konto")
public class KontoApplication {

	public static void main(String[] args) {

		SpringApplication.run(KontoApplication.class, args);
	}

}
