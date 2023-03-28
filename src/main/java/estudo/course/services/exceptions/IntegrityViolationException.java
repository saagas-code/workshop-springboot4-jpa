package estudo.course.services.exceptions;

public class IntegrityViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IntegrityViolationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IntegrityViolationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	

}
