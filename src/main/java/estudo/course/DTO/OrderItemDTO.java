package estudo.course.DTO;

import jakarta.validation.constraints.NotEmpty;

public class OrderItemDTO {
	@NotEmpty(message = "Campo productId é obrigatório.")
	private Long productId;
	
	@NotEmpty(message = "Campo quantity é obrigatório.")
	private Integer quantity;
	
	public OrderItemDTO(Long productId, Integer quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
