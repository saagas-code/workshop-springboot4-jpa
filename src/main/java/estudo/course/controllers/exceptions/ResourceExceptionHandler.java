package estudo.course.controllers.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import estudo.course.services.exceptions.IntegrityViolationException;
import estudo.course.services.exceptions.DatabaseException;
import estudo.course.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> handleInvalidDataException(MethodArgumentNotValidException ex, HttpServletRequest request) {
		String error = "Invalid data format";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		List<String> errors = new ArrayList<>();
	    for (FieldError errorr : ex.getBindingResult().getFieldErrors()) {
	        errors.add(errorr.getField() + ": " + errorr.getDefaultMessage());
	    }
		
		
		StandardError err = new StandardError(Instant.now(), status.value(), error, errors, request.getRequestURI());
		return ResponseEntity.status(status).body(err);
		
	}
	
	@ExceptionHandler(IntegrityViolationException.class)
	public ResponseEntity<StandardError> handleIntegrityException(IntegrityViolationException ex, HttpServletRequest request) {
		String error = "Integrity error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = ex.getMessage();

		StandardError err = new StandardError(Instant.now(), status.value(), error, message, request.getRequestURI());
		return ResponseEntity.status(status).body(err);

	}
}
