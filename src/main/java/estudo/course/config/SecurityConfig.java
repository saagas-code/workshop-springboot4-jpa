package estudo.course.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import estudo.course.security.FilterToken;

/**
	
	
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, jsr250Enabled = false)
public class SecurityConfig {
	
	@Autowired
	private FilterToken filter;
	
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {

		return http.csrf().disable().cors().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and()
				.exceptionHandling().authenticationEntryPoint(entryPoint)
					.and()
				.authorizeHttpRequests()
				.requestMatchers(HttpMethod.POST, "/users/auth").permitAll()
				.requestMatchers(HttpMethod.POST, "/users/register").permitAll()
				
				.requestMatchers(HttpMethod.GET, "/products/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/categories/**").permitAll()
				
				.requestMatchers(HttpMethod.GET, "/orders/**").permitAll()
				.requestMatchers("/api-docs").permitAll()
				.requestMatchers("/swagger-ui/**", "/v2/api-docs/**").permitAll()
				
				
				.anyRequest().authenticated()
					.and().addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.build();
				
	}		
					
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
