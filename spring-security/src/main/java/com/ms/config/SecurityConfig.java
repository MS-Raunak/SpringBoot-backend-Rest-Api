package com.ms.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


/**
 * It is just a basic authentication for only a single user:
 * user-name and password is mentioned in application.properties file
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(customizer -> customizer.disable()) //Customizer is an interface, it internally uses Customizer.customize() to get customizer object
		    .authorizeHttpRequests(authorize->authorize.anyRequest().authenticated())
		    //.formLogin(Customizer.withDefaults());//web-based login form 
		    .httpBasic(Customizer.withDefaults())  //postman or any non-backend
		    .sessionManagement(session -> session
		         		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //new session id will be created for every request: not work with web-based login form
		
		         return http.build();
	}

}
