package estudo.course.DTO;

import java.util.List;

public class ProductDTO {
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	
	private List<Long> categoriaIds;
	
	public ProductDTO() {
		
	}

	public ProductDTO(String name, String description, Double price, String imgUrl, List<Long> categoriaIds) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		this.categoriaIds = categoriaIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public List<Long> getCategoriaIds() {
		return categoriaIds;
	}

	public void setCategoriaIds(List<Long> categoriaIds) {
		this.categoriaIds = categoriaIds;
	}
	
	
	
	
}
