package estudo.course.repositories;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import estudo.course.entities.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByNameContainingIgnoreCase(String palavra1);
	
	List<Product> findByNameContainingIgnoreCaseAndNameContainingIgnoreCase(String palavra1, String palavra2);

	List<Product> findAll(Specification<Product> spec);
}
