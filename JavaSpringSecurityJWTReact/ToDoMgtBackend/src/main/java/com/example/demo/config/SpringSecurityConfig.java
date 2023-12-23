package com.example.demo.config;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.security.JwtAuthEntryPoint;
import com.example.demo.security.JwtAuthFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {
	
	
	private UserDetailsService userDetailsService;
	private JwtAuthEntryPoint jwtAuthEntryPoint;
	private JwtAuthFilter jwtAuthFilter;
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> csrf.disable())
		.authorizeHttpRequests((authorize)-> {
			
//			authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
//			authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
//			authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
//			authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
//			authorize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "USER");
//			a	uthorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
			authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
			authorize.requestMatchers("/api/auth/**").permitAll();
			
			authorize.anyRequest().authenticated();
			
		}).httpBasic(Customizer.withDefaults());
		
		
		http.exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthEntryPoint));
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
		
	}
	
	//In Memory Authentication Flow
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		
//		UserDetails arora = User.builder()
//				.username("arora").password(passwordEncoder().encode("arora")).roles("USER").build();
//		
//		UserDetails ramesh = User.builder()
//				.username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
//		
//		return new InMemoryUserDetailsManager(arora, ramesh);
//		
//		
//		
//	}
	
	
	
}
