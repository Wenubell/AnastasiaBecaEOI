package edu.es.eoi.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.es.eoi.user.domain.User;
import edu.es.eoi.user.repository.UserRepository;

class UserRestApplicationTests {

	@Test
	void testFindUserById() {

		UserRepository repo = new UserRepository();
		User user = repo.findById(9);

		assertThat(user.getNombre().contentEquals("jj"));

	}

	@Test
	void testCreateUser() {

		UserRepository repo = new UserRepository();
		User user =  new User();
		
		user.setFecha(Calendar.getInstance().getTime());
		user.setNombre("Prueba");
		user.setSaldo(33.3);
		user.setPremium(false);
		
		repo.create(user);

	}

}