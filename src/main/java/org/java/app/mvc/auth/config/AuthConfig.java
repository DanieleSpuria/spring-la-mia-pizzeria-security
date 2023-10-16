package org.java.app.mvc.auth.config;

import org.java.app.mvc.auth.serv.UserServ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests()
			.requestMatchers("/create-update").hasAuthority("ADMIN")
			.requestMatchers("/edit/**").hasAuthority("ADMIN")
			.requestMatchers("/delete/**").hasAuthority("ADMIN")
			.requestMatchers("/offerta/**").hasAuthority("ADMIN")
			.requestMatchers("/ingredienti").hasAuthority("ADMIN")
			.requestMatchers("/ingredienti/create").hasAuthority("ADMIN")
			.requestMatchers("/ingredienti/edit/**").hasAuthority("ADMIN")
			.requestMatchers("/ingredienti/**").hasAuthority("ADMIN")
			.requestMatchers("/**").permitAll()
			.and().formLogin()
			.and().logout().logoutSuccessUrl("/");
		
		return http.build();
	}
	
	@Bean
	UserServ userDetailsService() {
		
		return new UserServ();
	}
	
	@Bean 
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
}
