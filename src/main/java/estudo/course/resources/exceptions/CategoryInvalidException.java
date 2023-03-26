package estudo.course.resources.exceptions;

public class CategoryInvalidException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public CategoryInvalidException(String msg) {
		super(msg);
	}
}
