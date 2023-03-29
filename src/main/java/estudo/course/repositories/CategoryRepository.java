package estudo.course.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import estudo.course.entities.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	List<Category> findByNameContainingIgnoreCase(String name);
}
