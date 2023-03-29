package estudo.course.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import estudo.course.entities.Category;
import estudo.course.entities.Product;
import estudo.course.resources.DTO.ProductDTO;
import estudo.course.services.CategoryService;
import estudo.course.services.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll(@RequestParam(required = false) String name) {
		
		List<Product> list = new ArrayList<Product>();
		
		if (name != null) {
			list = service.findByName(name);
		} else {
			list = service.findAll();
		}
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping()
	public ResponseEntity<Product> create(@Valid @RequestBody ProductDTO obj) {
		Product product = modelMapper.map(obj, Product.class);
		
		Set<Category> categories = new HashSet<>();
		for (Long categoriaId : obj.getCategoriaIds()) {	
			Category categoryy = categoryService.findById(categoriaId);
			categories.add(categoryy);	
		}
		product.setCategories(categories);
		
		product = service.create(product);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(product);
	}
}
