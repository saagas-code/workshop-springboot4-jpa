package estudo.course.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String property, Object id) {
		super(property + " not found. Id " + id);
	}
	
}
