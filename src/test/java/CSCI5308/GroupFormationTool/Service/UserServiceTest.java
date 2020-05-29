package CSCI5308.GroupFormationTool.Service;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IUserService;
import CSCI5308.GroupFormationTool.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

	private IUserService userService;

	@Test
	void createUser() {
		userService = Injector.instance().getUserService();

		User user = new User();
		user.setBannerId("B00854462");
		user.setFirstName("padmesh");
		user.setLastName("donthu");
		user.setPassword("padmeshd");
		user.setEmailId("padmesh@gmail.com");
		user.setConfirmPassword("padmeshd");

		assertEquals(true, userService.createUser(user));
	}
}