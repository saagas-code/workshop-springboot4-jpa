package estudo.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import estudo.course.entities.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
