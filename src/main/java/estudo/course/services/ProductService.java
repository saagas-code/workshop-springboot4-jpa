package estudo.course.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import estudo.course.DTO.ProductDTO;
import estudo.course.entities.Category;
import estudo.course.entities.Product;
import estudo.course.repositories.ProductRepository;
import estudo.course.services.exceptions.IntegrityViolationException;
import estudo.course.services.exceptions.ResourceNotFoundException;
import estudo.course.specifications.ProductSpec;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryService categoryService;

	public List<Product> findAll() {

		List<Product> products = productRepository.findAll();

		return products;
	}

	public List<Product> findByName(String name) {

		String[] palavras = name.split(" ");
		if (palavras.length == 1) {
			return productRepository.findByNameContainingIgnoreCase(palavras[0]);
		} else {
			return productRepository.findByNameContainingIgnoreCaseAndNameContainingIgnoreCase(palavras[0],
					palavras[1]);
		}

	}

	public List<Product> findByNameAndCategory(String name, Long categoryId) {

		Specification<Product> spec = Specification.where(null);

		if (name != null) {
			spec = spec.and(ProductSpec.porNome(name));
		}
		if (categoryId != null) {
			spec = spec.and(ProductSpec.porCategoria(categoryId));
		}

		return productRepository.findAll(spec);

	}

	public Product findById(Long id) {
		Optional<Product> obj = productRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Product", id));
	}

	public Product create(ProductDTO obj) {

		Product product = modelMapper.map(obj, Product.class);

		Set<Category> categories = new HashSet<>();
		for (Long categoriaId : obj.getCategoriaIds()) {
			Category categoryy = categoryService.findById(categoriaId);
			categories.add(categoryy);
		}
		product.setCategories(categories);

		try {
			return productRepository.save(product);
		} catch (DataIntegrityViolationException e) {
			throw new IntegrityViolationException("Produto já existente");
		}
	}

	public Product update(Long id, ProductDTO obj) {

		Product product = modelMapper.map(obj, Product.class);

		Set<Category> categories = new HashSet<>();
		for (Long categoriaId : obj.getCategoriaIds()) {
			Category categoryy = categoryService.findById(categoriaId);
			categories.add(categoryy);
		}
		product.setCategories(categories);

		try {
			Product entity = productRepository.getReferenceById(id);
			updateData(entity, product);
			return productRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Product", id);
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
		Product product = this.findById(id);
		productRepository.delete(product);
	}

}
