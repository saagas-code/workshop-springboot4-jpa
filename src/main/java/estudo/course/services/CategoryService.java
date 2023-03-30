package estudo.course.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import estudo.course.DTO.CategoryDTO;
import estudo.course.entities.Category;
import estudo.course.repositories.CategoryRepository;
import estudo.course.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {
	private static final ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	public Category findById(Long id) {
		Optional<Category> obj = categoryRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Category", id));
	}
	
	public Category create(CategoryDTO obj) {
		Category category = modelMapper.map(obj, Category.class);
		
		return categoryRepository.save(category);
	}
	
	public void delete(Long id) {
		Category category = this.findById(id);
		categoryRepository.deleteById(category.getId());
	}
	
}
