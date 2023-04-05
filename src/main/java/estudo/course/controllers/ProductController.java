package estudo.course.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import estudo.course.DTO.ProductDTO;
import estudo.course.entities.Product;
import estudo.course.services.ProductService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll(@RequestParam(required = false) String name, @RequestParam(required = false) Long categoryId) {
		
		List<Product> list = productService.findByNameAndCategory(name, categoryId);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		
		Product obj = productService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping()
	@RolesAllowed("ROLE_ADMIN")
	public ResponseEntity<Product> create(@Valid @RequestBody ProductDTO obj) {

		Product product = productService.create(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(product);
	}
	
	@PutMapping(value = "{id}")
	@RolesAllowed("ROLE_ADMIN")
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductDTO obj) {
		
		Product product = productService.update(id, obj);
		return ResponseEntity.ok().body(product);
	}
	
	@DeleteMapping(value = "/{id}")
	@RolesAllowed("ROLE_ADMIN")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
