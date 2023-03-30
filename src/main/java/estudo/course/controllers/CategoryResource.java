package estudo.course.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import estudo.course.entities.Category;
import estudo.course.services.CategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	
	@PostMapping
	public ResponseEntity<Category> create(@Valid @RequestBody Category obj) {	
		obj = service.create(obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll(@RequestParam(required = false) String name) {
		
		List<Category> list = new ArrayList<Category>();
		
		if (name != null) {
			list = service.findByEmail(name);
		} else {
			list = service.findAll();
		}
		
		//List<Category> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Category category = service.findById(id);
		
		service.delete(category.getId());
		return ResponseEntity.noContent().build();
	}
	
}