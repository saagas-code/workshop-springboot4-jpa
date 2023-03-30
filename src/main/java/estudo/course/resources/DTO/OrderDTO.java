package estudo.course.resources.DTO;

import java.util.List;

public class OrderDTO {
	
	private Long clientId;
	
	
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
