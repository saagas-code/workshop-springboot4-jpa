package estudo.course.services.exceptions;

public class PasswordInvalidException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public PasswordInvalidException() {
		super("Senha inválida");
	}
	
}
