package estudo.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import estudo.course.entities.Product;
import estudo.course.repositories.ProductRepository;
import estudo.course.services.exceptions.IntegrityViolationException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public Product create(Product obj) {
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new IntegrityViolationException("Produto já existente");
		}
	}
	
	public List<Product> findAll() {
		return repository.findAll();
	}

	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}
}
