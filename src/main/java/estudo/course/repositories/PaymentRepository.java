package estudo.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import estudo.course.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
}
