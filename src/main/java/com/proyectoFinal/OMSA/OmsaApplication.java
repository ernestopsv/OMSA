package com.proyectoFinal.OMSA;

import com.proyectoFinal.OMSA.Services.UsuarioServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class OmsaApplication extends SpringBootServletInitializer{

	//@Autowired
	//static RolServices rolServices;

	public static void main(String[] args) {
		ApplicationContext appContext = SpringApplication.run(OmsaApplication.class, args);

//		UsuarioRepository usuarioRepository = (UsuarioRepository)appContext.getBean("usuarioRepository");
//
//		List<Usuario> usuarios = usuarioRepository.findAllByUsername("admin");
//		if(usuarios.size()<1){
//			Usuario usuario =  new Usuario();
//			usuario.setName("OMSA");
//			usuario.setUsername("admin");
//			usuario.setPassword("omsa1234");
//			usuarioRepository.save(usuario);
//			Rol rol = new Rol();
//			rol.setUsuario(usuario);
//			rol.setRol("ROLE_ADMIN");
//			rolServices.creacionRol(rol);
//		}
		UsuarioServices usuarioServices = (UsuarioServices)appContext.getBean("usuarioServices");
		usuarioServices.crearAdmin();

	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OmsaApplication.class);
	}

}
