package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
/**
@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		super.configure(http);
		http.csrf().disable();
		http.formLogin().disable();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserBuilder users = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder();

		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(users.username("user").password("password").roles("role1").build());
		manager.createUser(users.username("admin").password("password").roles("role2").build());
		return manager;
	}

}
*/

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	private UserDetailsService userDetailsService;

	public SpringSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService; }

	@Bean
    public UserDetailsService userDetailsService() {
        return this.userDetailsService;
    }
	 
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(configurer -> 
	  configurer.antMatchers(HttpMethod.POST,"/skills/api/auth/signin,/skills/api/auth/signup").permitAll()
				.antMatchers("/skills").permitAll()
				);
		 http.httpBasic(Customizer.withDefaults());
		http.csrf(csrf -> csrf.disable());
		
		SecurityFilterChain c = http.build();
		System.out.println("Filter:" + c);
		return c;
	}
}
