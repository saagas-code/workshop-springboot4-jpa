package estudo.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import estudo.course.entities.Product;
import estudo.course.repositories.ProductRepository;
import estudo.course.services.exceptions.IntegrityViolationException;
import estudo.course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll() {

		List<Product> products = repository.findAll();
		
		return products;
	}
	
	public List<Product> findByName(String name) {

		String[] palavras = name.split(" ");
        if (palavras.length == 1) {
            return repository.findByNameContainingIgnoreCase(palavras[0]);
        } else {
            return repository.findByNameContainingIgnoreCaseAndNameContainingIgnoreCase(palavras[0], palavras[1]);
        }

	}

	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Product create(Product obj) {
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new IntegrityViolationException("Produto já existente");
		}
	}
	
	public Product update(Long id, Product obj) {
		try {
			Product entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Product entity, Product obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setPrice(obj.getPrice());
		entity.setImgUrl(obj.getImgUrl());
		entity.setCategories(obj.getCategories());
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	
}
