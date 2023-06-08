package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {
	
	private UserDetailsService userDetailsService;

    public SpringSecurityConfig(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
                                 AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer -> 
		configurer.antMatchers(HttpMethod.GET, "/movies,/movies/**").hasRole("EMPLOYEE")
				.antMatchers(HttpMethod.POST, "/movies/**").hasRole("EMPLOYEE")
				.antMatchers(HttpMethod.PUT, "/movies/**/**").hasRole("EMPLOYEE")
				.antMatchers(HttpMethod.DELETE, "/movies/delete,/movies/delete/all").hasRole("EMPLOYEE"));
		http.httpBasic(Customizer.withDefaults());
		http.csrf(csrf -> csrf.disable());

		SecurityFilterChain c = http.build();
		System.out.println("Filter:" + c);
		return c;

	}
}
