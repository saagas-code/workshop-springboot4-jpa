package estudo.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estudo.course.entities.Category;
import estudo.course.resources.exceptions.CategoryInvalidException;
import estudo.course.services.CategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	
	@PostMapping
	@ExceptionHandler(CategoryInvalidException.class)
	public ResponseEntity<Category> create(@RequestBody @Valid Category obj) {
		//if (bindingResult.hasErrors()) {
	     //   throw new CategoryInvalidException(bindingResult.getFieldError("name").getDefaultMessage());
	    //}
		
		obj = service.create(obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		List<Category> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
