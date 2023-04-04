package estudo.course.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
	
	@Autowired
	private UserDetailsServiceImpl usuarioService;
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
	
	@Bean
	SecurityFilterChain  configure(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/users").permitAll()
				.requestMatchers("/users/**").permitAll()
				.requestMatchers("/categories/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers("/products/**").permitAll()
				.requestMatchers("/orders/**").permitAll()
				
				.anyRequest().authenticated()
			)
			.httpBasic()
			.and()
				.logout()
				.logoutSuccessUrl("/")
			.and()
				.exceptionHandling()
				.accessDeniedPage("/acesso-negado");

			return http.build();
	}
	
	
	
}
