package com.proyectoFinal.OMSA;

import com.proyectoFinal.OMSA.Services.ParadaServices;
import com.proyectoFinal.OMSA.Services.RutaServices;
import com.proyectoFinal.OMSA.Services.UsuarioServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class OmsaApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		ApplicationContext appContext = SpringApplication.run(OmsaApplication.class, args);

		UsuarioServices usuarioServices = (UsuarioServices)appContext.getBean("usuarioServices");
		usuarioServices.crearAdmin();


//		RutaServices rutaServices = (RutaServices)appContext.getBean("rutaServices");
//		rutaServices.guardarRutaAlPrincipio();
//
//		ParadaServices paradaServices =(ParadaServices)appContext.getBean("paradaServices");
//		paradaServices.guardarParadaAlPrincipio();

	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OmsaApplication.class);
	}

}
