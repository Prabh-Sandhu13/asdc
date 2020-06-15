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
	public void getIdTest() {
		IUser u = createDefaultUser();
		assertEquals(1, u.getId());
	}

	@Test
	public void setIdTest() {
		IUser u = new User();
		u.setId(2);
		assertEquals(2, u.getId());
	
	}

	@Test
	public void getFirstNameTest() {
		IUser u = createDefaultUser();
		assertEquals("Test", u.getFirstName());
	}

	@Test
	public void setFirstNameTest() {
		IUser u = new User();
		u.setFirstName("Padmesh");
		assertEquals("Padmesh",u.getFirstName());
	
	}

	@Test
	public void getLastNameTest() {
		IUser u = createDefaultUser();
		assertEquals("User", u.getLastName());
	}

	@Test
	public void setLastNameTest() {
		IUser u = new User();
		u.setLastName("Donthu");
		assertEquals("Donthu",u.getLastName());
	
	}

	@Test
	public void getBannerIdTest() {
		IUser u = createDefaultUser();
		assertEquals("B00854462", u.getBannerId());
	}

	@Test
	public void setBannerIdTest() {
		IUser u = new User();
		u.setBannerId("B0000000");
		assertEquals("B0000000",u.getBannerId());
	
	}

	@Test
	public void getEmailIdTest() {
		IUser u = createDefaultUser();
		assertEquals("padmeshdonthu@gmail.com", u.getEmailId());
	}

	@Test
	public void setEmailIdTest() {
		IUser u = new User();
		u.setBannerId("padmeshdonthu@gmail.com");
		assertEquals("padmeshdonthu@gmail.com",u.getBannerId());
	}

	@Test
	public void getPasswordTest() {
		IUser u = createDefaultUser();
		assertEquals("password", u.getPassword());
	}

	@Test
	public void setPasswordTest() {
		IUser u = new User();
		u.setPassword("password");
		assertEquals("password",u.getPassword());
	}

	@Test
	public void getConfirmPasswordTest() {
		IUser u = createDefaultUser();
		assertEquals("password", u.getConfirmPassword());
	}

	@Test
	public void setConfirmPasswordTest() {
		IUser u = new User();
		u.setConfirmPassword("password");
		assertEquals("password",u.getConfirmPassword());
	}
	
	@Test
	public void passwordSPolicyCheckTest() {
	}
	
}
