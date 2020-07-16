package edu.es.eoi.user.service;

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

	public void create(User e) {
		repository.create(e);
		
	}

}
