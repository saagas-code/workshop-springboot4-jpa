package estudo.course.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import estudo.course.entities.User;
import estudo.course.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	protected void doFilterInternal(
				HttpServletRequest request, 
				HttpServletResponse response, 
				FilterChain filterChain
			) throws ServletException, IOException {
		

			String token;
			
			var authorizationHeader = request.getHeader("Authorization");
			
			if(authorizationHeader != null) {
				token = authorizationHeader.replace("Bearer ", "");
				

				String subject = this.jwtService.getSubject(token);
				
				User user = this.userRepository.findByEmail(subject)
						.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		
				var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				System.out.println("testeeeeee");
				SecurityContextHolder.getContext().setAuthentication(authentication);

			}

		filterChain.doFilter(request, response);
	}
	
	public static class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(exception.getMessage());
        }
    }
	
}
