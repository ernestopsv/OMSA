package com.taptap.taptap;

import com.taptap.taptap.Service.UsuarioServices;
import com.taptap.taptap.config.SchedulerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@Import({SchedulerConfig.class})
@SpringBootApplication
public class TaptapApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		ApplicationContext appContext = SpringApplication.run(TaptapApplication.class, args);

		UsuarioServices usuarioServices = (UsuarioServices)appContext.getBean("usuarioServices");
		usuarioServices.crearAdmin();
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TaptapApplication.class);
	}

}
