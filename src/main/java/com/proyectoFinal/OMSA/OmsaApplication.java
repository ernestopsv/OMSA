package com.proyectoFinal.OMSA;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Properties;


//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
public class OmsaApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {


		ApplicationContext app = SpringApplication.run(OmsaApplication.class, args);
//		Properties properties = new Properties();
//		properties.setProperty("spring.resources.staticLocations","classpath:/resources/static/");
//
//		app.setDefaultProperties(properties);
		//app.run(args);


	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OmsaApplication.class);
	}

}
