package CSCI5308.GroupFormationTool.Service;

import CSCI5308.GroupFormationTool.DBMock.UserDBMock;
import CSCI5308.GroupFormationTool.Model.User;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

	@Test
	void createUser() {

		UserDBMock userDBMock = new UserDBMock();
		User user = new User();
		user.setBannerId("B00854462");
		user.setEmailId("padmeshdonthu1@gmail.com");
		user.setFirstName("padmesh");
		user.setLastName("donthu");
		user.setPassword("padmesh1234");

		assertEquals(null, userDBMock.getUserByEmailId(user));

		assertEquals(true, userDBMock.createUser(user));

	}
}