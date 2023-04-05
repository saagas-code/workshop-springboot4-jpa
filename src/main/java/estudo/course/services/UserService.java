package estudo.course.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import estudo.course.DTO.CredentialsDTO;
import estudo.course.DTO.UserDTO;
import estudo.course.entities.User;
import estudo.course.repositories.UserRepository;
import estudo.course.services.exceptions.DatabaseException;
import estudo.course.services.exceptions.IntegrityViolationException;
import estudo.course.services.exceptions.PasswordInvalidException;
import estudo.course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService implements UserDetailsService {
	
	private static final ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private UserRepository repository;
	
	public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("User", id));
	}
	
	public User insert(UserDTO obj) {
		
		String passCrypted = passwordEncoder().encode(obj.getPassword());
		obj.setPassword(passCrypted);
		
		User user = modelMapper.map(obj, User.class);
		user.setRole("USER");
		
		try {
			return repository.save(user);
		} catch (DataIntegrityViolationException e) {
			throw new IntegrityViolationException("Email já existente");
		}
	}
	
	public User update(Long id, UserDTO obj) {
		
		User user = modelMapper.map(obj, User.class);
		
		try {
			User entity = repository.getReferenceById(id);
			updateData(entity, user);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("User", id);
		}
		
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
	
	public void delete(Long id) {
		
		User user = this.findById(id);
		
		try {
			repository.deleteById(user.getId());
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("User", id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public User authenticate(CredentialsDTO credentials) {
		User user = repository.findByEmail(credentials.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("Email", credentials.getEmail()));
		
		UserDetails userr = loadUserByUsername(user.getEmail());
		boolean passwordOk = this.passwordEncoder().matches(credentials.getPassword(), userr.getPassword());

		if(passwordOk) {
			return user;
		}
		throw new PasswordInvalidException();

	}
	
	@Override
	public UserDetails loadUserByUsername(String email) {

		return repository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
	}
	
	/*
	@Override
	public UserDetails loadUserByUsername(String email) {

		User user = repository.findByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		
		String[] roles = user.getRole().equals("admin") ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};
		
		return org.springframework.security.core.userdetails.User
				.builder()
				.username(user.getEmail())
				.password(user.getPassword())
				.roles(roles)
				.build();
				
	}
	*/
	
	
	
}
