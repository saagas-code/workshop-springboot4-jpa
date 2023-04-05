package estudo.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import estudo.course.services.UserDetailsServiceImpl;

/**
	
	
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/users").hasRole("ADMIN")
				.requestMatchers("/users/register").permitAll()
				.requestMatchers("/users/**").hasRole("USER")
				.requestMatchers("/products/**").permitAll()
				.requestMatchers("/orders/**").permitAll()
				.anyRequest().authenticated())
				
				
				.httpBasic()
					.and()
					
				.logout()
				.logoutSuccessUrl("/");
		
		return http.build();
	}
	

	@Bean
		AuthenticationManager authenticationManager(HttpSecurity http,
					PasswordEncoder passwordEncoder,
					UserDetailsServiceImpl usuarioService) throws Exception {
			return http.getSharedObject(AuthenticationManagerBuilder.class)
	            .userDetailsService(usuarioService)
	               .passwordEncoder(passwordEncoder)
		           	.and()
		           .build();
		}

}
