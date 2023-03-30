package estudo.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import estudo.course.entities.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
