package estudo.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import estudo.course.entities.Category;
import estudo.course.repositories.CategoryRepository;
import estudo.course.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public Category create(Category obj) {
		return repository.save(obj);
	}
	
	public List<Category> findAll() {
		return repository.findAll();
	}
	
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Category", id));
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
