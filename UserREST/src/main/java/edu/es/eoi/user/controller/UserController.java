package edu.es.eoi.user.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.es.eoi.user.domain.User;
import edu.es.eoi.user.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping("users/{id}")
	public User findUserByIdUsuario(@PathVariable Integer id) {
		return service.findUserById(id);
	}
	
	@PostMapping("users")
	public void createUser(@RequestBody User user) {
		
		user.setFecha(Calendar.getInstance().getTime());
		service.create(user);
	}

}
