package com.proyectoFinal.OMSA;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@SpringBootApplication
public class OmsaApplication {
	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(OmsaApplication.class);
		Properties properties = new Properties();
		properties.setProperty("spring.resources.staticLocations",
				"classpath:/templates");
		app.setDefaultProperties(properties);

		app.run(args);
	}

}
