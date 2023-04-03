package estudo.course.DTO;

import jakarta.validation.constraints.NotEmpty;

public class UserDTO {
	
	@NotEmpty(message = "Campo name é obrigatório.")
	private String name;
	@NotEmpty(message = "Campo email é obrigatório.")
	private String email;
	@NotEmpty(message = "Campo phone é obrigatório.")
	private String phone;
	@NotEmpty(message = "Campo password é obrigatório.")
	private String password;
	
	public UserDTO() {
		
	}
	
	public UserDTO(String name, String email, String phone, String password) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
}
