package edu.es.eoi.user.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import edu.es.eoi.user.entity.User;

@Repository
@Qualifier("JPA")
public class UserRepositoryJPAImpl implements MyRepository<User> {

	@Autowired
	private EntityManager manager;

	@Override
	public User findById(Integer id) {
		return manager.find(User.class, id);
	}

	@Override
	public void create(User e) {
		manager.merge(e);
	}

	@Override
	public void update(User e) {
		manager.merge(e);

	}

	@Override
	public void deleteById(Integer id) {
		manager.remove(findById(id));

	}

	@Override
	public List<User> findAll() {
		return manager.createQuery("from User", User.class).getResultStream().collect(Collectors.toList());
	}

}