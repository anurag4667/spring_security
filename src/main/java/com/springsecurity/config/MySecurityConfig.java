package com.springsecurity.config;

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
public class MySecurityConfig  {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		//session management being stateless means ne sesssion id ill be generated this ill not ork in broser but ill ork ith postman
		http
		.csrf(customizer -> customizer.disable())
		.authorizeHttpRequests(request -> request.anyRequest().authenticated())
		.formLogin(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults())
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        
        return http.build();
    }
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails user1 = User
				.withDefaultPasswordEncoder()
				.username("anurag")
				.password("hello")
				.roles("user")
				.build();
		
		return new InMemoryUserDetailsManager(user1);
	}
	
	
}
