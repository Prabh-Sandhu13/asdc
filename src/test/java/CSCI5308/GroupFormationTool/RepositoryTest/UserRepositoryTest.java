package CSCI5308.GroupFormationTool.RepositoryTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.Model.User;
import CSCI5308.GroupFormationTool.Repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

	private UserRepository userRepository;

	@Test
	public void createUser() {

		userRepository = mock(UserRepository.class);

		User user = new User();
		when(userRepository.createUser(user)).thenReturn(true);

		assertEquals(true, userRepository.createUser(user));
	}

	@Test
	public void getUserByEmailId() {

		userRepository = mock(UserRepository.class);

		User user = new User();
		when(userRepository.getUserByEmailId(user)).thenReturn(null);

		assertEquals(null, userRepository.getUserByEmailId(user));
	}

	@Test
	public void getUserByBannerId() {
		
		userRepository = mock(UserRepository.class);

		User user = new User();
		when(userRepository.getUserByBannerId(user)).thenReturn(null);

		assertEquals(null, userRepository.getUserByBannerId(user));
	}

}
