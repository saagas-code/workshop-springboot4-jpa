package estudo.course.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import estudo.course.DTO.CredentialsDTO;
import estudo.course.DTO.UserDTO;
import estudo.course.entities.User;
import estudo.course.security.JwtService;
import estudo.course.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping
	@Operation(summary = "Get all Users", description = "Return a list of users")
	@RolesAllowed("ROLE_ADMIN")
	public ResponseEntity<List<User>> findAll() {
		List<User> list = userService.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	@Operation(summary = "Get one user by ID")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = userService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping(value = "/{id}")
	@Operation(summary = "Update one User")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserDTO obj) {
		User user = userService.update(id,  obj);
		return ResponseEntity.ok().body(user);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delete one user by ID")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		userService.delete(id);
		return ResponseEntity.noContent().build();

	}
	
	@PostMapping("/register")
	@Operation(summary = "Create one User")
	public ResponseEntity<User> create(@Valid @RequestBody UserDTO obj) {
		User user = userService.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
	//@PostMapping("/auth")
	//public ResponseEntity<TokenDTO> authenticate(@Valid @RequestBody CredentialsDTO credentials) {
	//			
	//	User user = userService.authenticate(credentials);
	//	
	//	String token = jwtService.getToken(user);
	//	TokenDTO tokenData = new TokenDTO(user.getEmail(), token);
	//	return ResponseEntity.ok().body(tokenData);
	//		
	//	
	//}
	
	@PostMapping("/auth")
	public String authenticate(@Valid @RequestBody CredentialsDTO credentials) {
				
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				credentials.getEmail(), 
				credentials.getPassword()
		);
		Authentication authenticate = this.authenticationManager
				.authenticate(usernamePasswordAuthenticationToken);
		
		var user = (User)authenticate.getPrincipal();

		return jwtService.getToken(user);
		
	}
	
	
}
