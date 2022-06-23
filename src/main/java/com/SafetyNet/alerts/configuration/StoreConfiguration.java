package com.SafetyNet.alerts.configuration;

import java.io.IOException;
import java.net.URL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.SafetyNet.alerts.model.Store;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@ComponentScan(basePackages = {"com.SafetyNet.alerts"})
public class StoreConfiguration {
	
	@Bean
	public Store store() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		URL url = new URL("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json");
		Store store = objectMapper.readValue(url, Store.class);
		return store;
	}
}
