package estudo.course.DTO;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

public class OrderDTO {
	
	@NotEmpty(message = "Campo clientId é obrigatório.")
	private Long clientId;
	
	@NotEmpty(message = "Campo items é obrigatório.")
	private List<OrderItemDTO> items;
	
	public OrderDTO(Long clientId, List<OrderItemDTO> items) {
		super();
		this.clientId = clientId;
		this.items = items;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public List<OrderItemDTO> getItems() {
		return items;
	}
	public void setItems(List<OrderItemDTO> items) {
		this.items = items;
	}
	
	
}
