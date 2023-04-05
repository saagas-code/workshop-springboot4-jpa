package estudo.course.services.exceptions;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenExpiredException extends AuthenticationException  {
	private static final long serialVersionUID = 1L;

	public JwtTokenExpiredException(String message, Throwable cause) {
		super(message, cause);
	}

	public JwtTokenExpiredException(String message) {
		super(message);
	}
}
