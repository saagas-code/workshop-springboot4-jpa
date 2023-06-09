package estudo.course.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import estudo.course.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
