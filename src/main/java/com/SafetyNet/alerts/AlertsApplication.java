package com.SafetyNet.alerts;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.SafetyNet.alerts"})
public class AlertsApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AlertsApplication.class, args);
		
	}

}