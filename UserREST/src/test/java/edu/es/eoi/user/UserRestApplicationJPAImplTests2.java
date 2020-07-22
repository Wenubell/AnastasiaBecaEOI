package edu.es.eoi.user;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import edu.es.eoi.user.entity.User;
import edu.es.eoi.user.repository.MyRepository;
import edu.es.eoi.user.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = UserRestApplication.class)
class UserRestApplicationJPAImplTests2 {

	@Autowired
	@Qualifier("JPA")
	MyRepository<User> repository;
	
	@Autowired
	UserService service;

	@Test
	void testFindUserById() {
		assertEquals("JJ", repository.findById(1).getNombre());
	}

	@Test
	void testCreateUser() {

		User user = new User();
		user.setNombre("prueba");
		repository.create(user);	
	}

	@Test
	void testDeleteUser() throws Exception {
		service.deleteById(2);
	}

}