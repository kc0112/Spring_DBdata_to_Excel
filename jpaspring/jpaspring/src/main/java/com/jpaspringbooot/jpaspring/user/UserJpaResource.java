package com.jpaspringbooot.jpaspring.user;

import com.jpaspringbooot.jpaspring.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {

	@Autowired
	private UserRepository userRepository;

	public UserJpaResource(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}


	@GetMapping("/jpa/users/{id}")
	public User retrieveUser(@PathVariable int id) {

		// null hua to be shi
		Optional<User> optionalUser = userRepository.findById(id);

		if (optionalUser.isEmpty())
			throw new UserNotFoundException("id:" + id);

		User user = optionalUser.get();
		return user;
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}


	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

		User savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
}