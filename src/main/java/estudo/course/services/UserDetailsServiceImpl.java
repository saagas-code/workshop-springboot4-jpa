package estudo.course.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	//@Autowired
	//private UserRepository repository;
		
	public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//estudo.course.entities.User user = repository.findByEmail(username)
		//	.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		
		//String[] roles = user.isAdmin() ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};
		
		return User
				.builder()
				.username("admin")
				.password(this.passwordEncoder().encode("admin"))
				.roles("USER")
				.build();
				
	}
}
