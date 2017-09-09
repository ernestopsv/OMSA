package com.proyectoFinal.OMSA.config;

/**
 * Created by Dany on 06/09/2017.
 */
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configurable
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ConfiguracionDeSeguridad extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //Cargando los usuarios en memoria.
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("omsa1234")
//                .roles("ADMIN","USER");
////                .and()
////                .withUser("user")
////                .password("omsa1234")
////                .roles("USER");
//    }

    /*
     * Permite configurar las reglas de seguridad.
     * @param http
     * @throws Exception
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Marcando las reglas para permitir unicamente los usuarios
        http.authorizeRequests()
                .antMatchers( "/autobus/**","/parada/**", "/ruta/**", "/coordenada/**").hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated()
                .antMatchers("/admin/**")
                .hasRole("ADMIN").anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login") //indicando la ruta que estaremos utilizando.
                .failureUrl("/login?error=true").defaultSuccessUrl("/")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}