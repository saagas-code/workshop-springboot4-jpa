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
	
	public List<Product> findAll() {

		List<Product> products = repository.findAll();
		
		return products;
	}
	
	public List<Product> findByName(String name) {
		//return repository.findAll();
		
		String[] palavras = name.split(" ");
        if (palavras.length == 1) {
            return repository.findByNameContainingIgnoreCase(palavras[0]);
        } else {
            return repository.findByNameContainingIgnoreCaseAndNameContainingIgnoreCase(palavras[0], palavras[1]);
        }

	}

	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}
	
	public Product create(Product obj) {
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new IntegrityViolationException("Produto já existente");
		}
	}
	
	
}
