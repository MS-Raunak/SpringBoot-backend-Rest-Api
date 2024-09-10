package com.ms.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
@EnableWebSecurity
public class SecurityConfig {
    /**
     * It is just a basic authentication for only a single user:
     * user-name and password is mentioned in application.properties file
     */
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
    

    
    //Database authentication
	
	@Autowired
	UserDetailsService userDetailsService;
	
    @Bean
    AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		
		return daoAuthenticationProvider;
	}
	
	//Password encoder
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
