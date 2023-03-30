package estudo.course.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import estudo.course.entities.Order;
import estudo.course.entities.OrderItem;
import estudo.course.entities.Payment;
import estudo.course.entities.Product;
import estudo.course.entities.User;
import estudo.course.entities.enums.OrderStatus;
import estudo.course.repositories.OrderItemRepository;
import estudo.course.repositories.OrderRepository;
import estudo.course.repositories.PaymentRepository;
import estudo.course.resources.DTO.OrderDTO;
import estudo.course.resources.DTO.OrderItemDTO;
import estudo.course.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private UserService userRepository;
	
	@Autowired
	private ProductService productService;
	
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	public Order findById(Long id) {
		Optional<Order> obj = orderRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Order", id));
	}
	
	public Order create(OrderDTO orderDTO) {
		User user = userRepository.findById(orderDTO.getClientId());
		
		Order order = new Order();
		order.setClient(user);
		order.setMoment(Instant.now());
		order.setOrderStatus(OrderStatus.WAITING_PAYMENT);
		
		for (OrderItemDTO itemDTO : orderDTO.getItems()) {
			Product product = productService.findById(itemDTO.getProductId());
			
			OrderItem item = new OrderItem();
			item.setOrder(order);
			item.setProduct(product);
			item.setQuantity(itemDTO.getQuantity());
			item.setPrice(product.getPrice());
			
			order.getItems().add(item);
		
		}
		
		order = orderRepository.save(order);
		orderItemRepository.saveAll(order.getItems());
		
		Payment payment = new Payment();
		payment.setOrder(order);
		payment.setMoment(Instant.now());
		payment.setClient(user);
		payment = paymentRepository.save(payment);

		order.setPayment(payment);
		
		
		return order;
	}
	
	public void deleteById(Long id) {
		Order order = this.findById(id);
		
		orderRepository.deleteById(order.getId());

	}
}
