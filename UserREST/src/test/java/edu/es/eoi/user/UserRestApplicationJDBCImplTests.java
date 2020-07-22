package edu.es.eoi.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.es.eoi.user.domain.User;
import edu.es.eoi.user.repository.UserRepositoryJDBCImpl;

class UserRestApplicationJDBCImplTests {

	@Test
	void testFindUserById() {

		UserRepositoryJDBCImpl repo = new UserRepositoryJDBCImpl();
		User user = repo.findById(1);

		assertThat(user.getNombre().contentEquals("JJ"));

	}

	@Test
	void testCreateUser() {

		UserRepositoryJDBCImpl repo = new UserRepositoryJDBCImpl();
		User user =  new User();
		
		user.setFecha(Calendar.getInstance().getTime());
		user.setNombre("Prueba");
		user.setSaldo(33.3);
		user.setPremium(false);
		
		repo.create(user);

	}
	
	@Test
	void testUpdateUser() {

		UserRepositoryJDBCImpl repo = new UserRepositoryJDBCImpl();
		User user =  repo.findById(1);
		
		user.setFecha(Calendar.getInstance().getTime());
		user.setNombre("update");
		user.setSaldo(33.3);
		user.setPremium(true);
		
		repo.update(user);

	}

}
