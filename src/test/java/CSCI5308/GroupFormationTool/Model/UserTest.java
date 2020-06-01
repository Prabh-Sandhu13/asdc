package CSCI5308.GroupFormationTool.Model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.DBMock.UserDBMock;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserTest {
	
	
	private IUser createDefaultUser()
	{
		UserDBMock userDbMock = new UserDBMock();
		IUser user = loadUser(userDbMock);
		return user;
	}
	
	private IUser loadUser(UserDBMock userDBMock) {
		
		IUser user = new User();		
		user = userDBMock.loadUserWithID(user);
		return user;
	}

	@Test
	public void getId() {
		IUser u = createDefaultUser();
		assertEquals(1, u.getId());
	}

	@Test
	public void setId() {
		IUser u = new User();
		u.setId(2);
		assertEquals(2, u.getId());
	
	}

	@Test
	public void getFirstName() {
		IUser u = createDefaultUser();
		assertEquals("Test", u.getFirstName());
	}

	@Test
	public void setFirstName() {
		IUser u = new User();
		u.setFirstName("Padmesh");
		assertEquals("Padmesh",u.getFirstName());
	
	}

	@Test
	public void getLastName() {
		IUser u = createDefaultUser();
		assertEquals("User", u.getLastName());
	}

	@Test
	public void setLastName() {
		IUser u = new User();
		u.setLastName("Donthu");
		assertEquals("Donthu",u.getLastName());
	
	}

	@Test
	public void getBannerId() {
		IUser u = createDefaultUser();
		assertEquals("B00854462", u.getBannerId());
	}

	@Test
	public void setBannerId() {
		IUser u = new User();
		u.setBannerId("B0000000");
		assertEquals("B0000000",u.getBannerId());
	
	}

	@Test
	public void getEmailId() {
		IUser u = createDefaultUser();
		assertEquals("padmeshdonthu@gmail.com", u.getEmailId());
	}

	@Test
	public void setEmailId() {
		IUser u = new User();
		u.setBannerId("padmeshdonthu@gmail.com");
		assertEquals("padmeshdonthu@gmail.com",u.getBannerId());
	}

	@Test
	public void getPassword() {
		IUser u = createDefaultUser();
		assertEquals("password", u.getPassword());
	}

	@Test
	public void setPassword() {
		IUser u = new User();
		u.setPassword("password");
		assertEquals("password",u.getPassword());
	}

	@Test
	public void getConfirmPassword() {
		IUser u = createDefaultUser();
		assertEquals("password", u.getConfirmPassword());
	}

	@Test
	public void setConfirmPassword() {
		IUser u = new User();
		u.setConfirmPassword("password");
		assertEquals("password",u.getConfirmPassword());
	}
	
}
