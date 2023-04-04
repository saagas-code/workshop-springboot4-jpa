package estudo.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	UserDetailsService adminDetailsService() {
		UserDetails admin = 
				User.builder()
					.username("admin")
					.password(passwordEncoder().encode("admin"))
					.roles("ADMIN")
					.build();
		
		return new InMemoryUserDetailsManager(admin);
	}
	
	//@Bean
	//AuthenticationManager authenticationManager(HttpSecurity http,
	//			PasswordEncoder passwordEncoder,
	//			UserDetailsService userDetailsService) throws Exception {
	//	return http.getSharedObject(AuthenticationManagerBuilder.class)
	//               .userDetailsService(userDetailsService)
	//               .passwordEncoder(passwordEncoder)
	//            .and()
	//             .build();
	//

	@Bean
	SecurityFilterChain  configure(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/users/**").permitAll()
				.requestMatchers("/categories/**").hasRole("ADMIN")
				
				.anyRequest().authenticated()
			)
			.formLogin()
				.permitAll()
			.and()
				.logout()
				.logoutSuccessUrl("/")
			.and()
				.exceptionHandling()
				.accessDeniedPage("/acesso-negado")
			.and()
				.rememberMe();
			
			return http.build();
	}
	
	

	//@Bean
    //SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    //    return http
    //            .authorizeExchange()
    //                .pathMatchers("/users/**").permitAll()
    //                .pathMatchers("/categories/**").hasRole("ADMIN")
    //                .anyExchange().authenticated()
    //                .and()
    //            .formLogin()
    //                .and()
    //            .httpBasic()
    //                .and()
    //            .csrf().disable()
    //            .build();
    //}

}
