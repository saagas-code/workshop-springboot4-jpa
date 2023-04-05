package estudo.course.services.exceptions;

public class JwtTokenExpiredException extends RuntimeException  {
	private static final long serialVersionUID = 1L;

	public JwtTokenExpiredException(String message, Throwable cause) {
		super(message, cause);
	}

	public JwtTokenExpiredException(String message) {
		super(message);
	}
}
