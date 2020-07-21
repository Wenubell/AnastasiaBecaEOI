package edu.es.eoi.user.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.es.eoi.user.domain.User;
import edu.es.eoi.user.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService service;

	@GetMapping("users/{id}")
	public ResponseEntity<User> findUserByIdUsuario(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findUserById(id));
	}

	@PostMapping("users")
	public ResponseEntity<String> createUser(@RequestBody User user) {

		user.setFecha(Calendar.getInstance().getTime());
		service.create(user);

		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@PutMapping("users/{id}")
	public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody User user) {

		if (user.getSaldo() == null) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		if (!id.equals(user.getId())) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} else {
			// user.setId(id);
			// user.setFecha(Calendar.getInstance().getTime());
			service.update(user);
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}

	}

	@DeleteMapping("users/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Integer id) {
		service.deleteById(id);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

	@GetMapping("users")
	public ResponseEntity<List<User>> finAll() {
		return ResponseEntity.ok(service.findAll());
	}

}
