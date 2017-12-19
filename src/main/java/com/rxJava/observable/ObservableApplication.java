package com.rxJava.observable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.rxJava.observable")
public class ObservableApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObservableApplication.class, args);
	}
}
