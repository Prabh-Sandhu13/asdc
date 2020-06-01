package CSCI5308.GroupFormationTool.Service;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.Model.User;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

	UserService userService = new UserService();

	@Test
	void createUserTest() {

		IUser user = new User();
		user.setBannerId("B00854462");
		user.setEmailId("test@gmail.com");
		user.setFirstName("");
		user.setLastName("donthu");
		user.setPassword("padmesh1234");
		user.setConfirmPassword("padmesh1111");

		assertEquals(false,userService.createUser(user));

		
	}
}