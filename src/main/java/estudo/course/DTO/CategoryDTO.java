package estudo.course.DTO;

import jakarta.validation.constraints.Size;

public class CategoryDTO {
	private String name;
	
	public CategoryDTO() {
	}
	
	public CategoryDTO(String name) {
		super();
		this.name = name;
	}

	@Size(min = 2, max = 10, message = "O nome deve conter mais que 2 ou menos de 10 caracteres")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
