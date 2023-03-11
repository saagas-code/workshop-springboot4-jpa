package estudo.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import estudo.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
