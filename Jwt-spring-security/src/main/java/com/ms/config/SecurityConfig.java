package com.ms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(customizer->customizer.disable())
			.authorizeHttpRequests(request -> request
					.requestMatchers("/register", "login").permitAll()
					//.requestMatchers("/login").permitAll()
					.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		
		return daoAuthenticationProvider;
	}
	
	
	//Jwt 
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	  return config.getAuthenticationManager();
	}

}
