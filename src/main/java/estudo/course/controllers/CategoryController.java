package estudo.course.controllers;

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

import estudo.course.DTO.CategoryDTO;
import estudo.course.entities.Category;
import estudo.course.services.CategoryService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll(@RequestParam(required = false) String name) {
					
		List<Category> list = categoryService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category obj = categoryService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	@RolesAllowed("ROLE_ADMIN")
	public ResponseEntity<Category> create(@Valid @RequestBody CategoryDTO obj) {	
		Category category = categoryService.create(obj);
		return ResponseEntity.ok().body(category);
	}
	
	@DeleteMapping(value = "/{id}")
	@RolesAllowed("ROLE_ADMIN")
	public ResponseEntity<?> delete(@PathVariable Long id) {	
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
