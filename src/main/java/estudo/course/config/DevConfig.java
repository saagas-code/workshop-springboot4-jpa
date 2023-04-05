package estudo.course.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import estudo.course.entities.User;
import estudo.course.repositories.UserRepository;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		
		try {
			userRepository.findByEmail("admin@gmail.com");
		} catch (RuntimeException e) {
			User user = new User(null, "admin", "admin@gmail.com", passwordEncoder.encode("admin"), "admin");

			userRepository.save(user);
		}
		
		//User user = new User(null, "admin", "admin@gmail.com", "admin", "admin");
//
		//userRepository.save(user);
	} 
	
	
}
