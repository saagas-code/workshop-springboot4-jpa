package estudo.course.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estudo.course.DTO.OrderDTO;
import estudo.course.entities.Order;
import estudo.course.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@GetMapping
	@Operation(summary = "Get a list of all Orders")
	public ResponseEntity<List<Order>> findAll() {
		List<Order> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	@Operation(summary = "Get one Order by ID")
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping()
	@Operation(summary = "Create one Order")
	public ResponseEntity<Order> create(@Valid @RequestBody OrderDTO orderDTO) {
		Order order = service.create(orderDTO);
		return ResponseEntity.ok().body(order);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delete one Order by ID")
	public ResponseEntity<Order> delete(@PathVariable Long id) {
		Order order = service.findById(id);
		service.deleteById(order.getId());
		
		return ResponseEntity.noContent().build();
	}
}
