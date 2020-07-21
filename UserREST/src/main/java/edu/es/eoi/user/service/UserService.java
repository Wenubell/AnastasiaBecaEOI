package edu.es.eoi.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.es.eoi.user.domain.User;
import edu.es.eoi.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;

	public User findUserById(Integer id) {
		return repository.findById(id);
	}

	public void create(User user) {
		repository.create(user);
		
	}

	public void update(User user) {
		repository.update(user);
		
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
		
	}
	
	public List<User> findAll() {
		return repository.findAll();
		
	}

}
