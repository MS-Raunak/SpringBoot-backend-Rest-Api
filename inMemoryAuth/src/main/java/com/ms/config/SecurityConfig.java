package com.ms.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	//In-memory authentication: user not comes from database rather than it is memorize 2 users
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user1 = User
				              .withDefaultPasswordEncoder() //just for avoid bcrypt password
				              .username("ram")
				              .password("123")
				              .roles("USER") //optional
				              .build();
		
		UserDetails user2 = User
				.withDefaultPasswordEncoder()
				.username("krishna")
				.password("1234")
				.roles("USER")
				.build();
		
		return new InMemoryUserDetailsManager(user1,user2);
	}

}
