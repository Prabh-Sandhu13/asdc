package CSCI5308.GroupFormationTool.RepositoryTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.Model.User;
import CSCI5308.GroupFormationTool.Repository.ForgotPasswordRepository;
import CSCI5308.GroupFormationTool.Repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ForgotPasswordRepositoryTest {
	
	private ForgotPasswordRepository forgotPasswordRepository;
	User user = new User();
	
	@Test
	public void addTokenTest() {
		forgotPasswordRepository = mock(ForgotPasswordRepository.class);
		
		when(forgotPasswordRepository.addToken(user,"sampleToken")).thenReturn(true);
		assertEquals(true, forgotPasswordRepository.addToken(user,"sampleToken"));
	}
	
	@Test
	public void getTokenTest() {
		forgotPasswordRepository = mock(ForgotPasswordRepository.class);
		when(forgotPasswordRepository.getToken(user)).thenReturn(null);
		assertEquals(null, forgotPasswordRepository.getToken(user));
	}
	
	@Test
	public void updatePasswordTest() {
		forgotPasswordRepository = mock(ForgotPasswordRepository.class);
		when(forgotPasswordRepository.updatePassword(user,"encryptedPassword")).thenReturn(true);
		assertEquals(true, forgotPasswordRepository.updatePassword(user,"encryptedPassword"));
	}
	
	@Test
    public void updateTokenTest() {
		forgotPasswordRepository = mock(ForgotPasswordRepository.class);
		when(forgotPasswordRepository.updateToken(user,"newToken")).thenReturn(true);
		assertEquals(true, forgotPasswordRepository.updateToken(user,"newToken"));
    }
	
	@Test
    public void deleteTokenTest() {
		forgotPasswordRepository = mock(ForgotPasswordRepository.class);
		when(forgotPasswordRepository.deleteToken(user,"token")).thenReturn(true);
		assertEquals(true, forgotPasswordRepository.deleteToken(user,"token"));
	}
	
	@Test
    public void getUserIdTest() {
		
		forgotPasswordRepository = mock(ForgotPasswordRepository.class);
		when(forgotPasswordRepository.getUserId(user)).thenReturn(null);
		assertEquals(null, forgotPasswordRepository.getUserId(user));
		
	}
	
	@Test
    public void getEmailByTokenTest() {
		
		forgotPasswordRepository = mock(ForgotPasswordRepository.class);
		when(forgotPasswordRepository.getEmailByToken(user,"token")).thenReturn(null);
		assertEquals(null, forgotPasswordRepository.getEmailByToken(user,"token"));
		
	}
	
	@Test
	public void getSettingvalueTest() {
		forgotPasswordRepository = mock(ForgotPasswordRepository.class);
		when(forgotPasswordRepository.getSettingValue("Password History")).thenReturn("value");
		when(forgotPasswordRepository.getSettingValue(null)).thenReturn(null);
		
		assertEquals(null, forgotPasswordRepository.getSettingValue(null));
		assertEquals("value", forgotPasswordRepository.getSettingValue("Password History"));
	}
	
	@Test
	public void getNPasswordsTest() {
		ArrayList<String> nPasswords = new ArrayList<String>();
		nPasswords.add("hostory1");
		nPasswords.add("hostory2");
		nPasswords.add("hostory3");
		
		forgotPasswordRepository = mock(ForgotPasswordRepository.class);
		when(forgotPasswordRepository.getNPasswords(user, "3")).thenReturn(nPasswords);
		when(forgotPasswordRepository.getNPasswords(user, null)).thenReturn(null);
		when(forgotPasswordRepository.getNPasswords(null, "3")).thenReturn(null);
		
		assertEquals(null, forgotPasswordRepository.getNPasswords(user, null));
		assertEquals(null, forgotPasswordRepository.getNPasswords(null, "3"));
		assertEquals(3, forgotPasswordRepository.getNPasswords(user, "3").size());
	}
	
	@Test
	public void addPasswordHistoryTest() {
		forgotPasswordRepository = mock(ForgotPasswordRepository.class);
		when(forgotPasswordRepository.addPasswordHistory(user, "")).thenReturn(false);
		when(forgotPasswordRepository.addPasswordHistory(user, null)).thenReturn(false);
		when(forgotPasswordRepository.addPasswordHistory(null,"password")).thenReturn(false);
		when(forgotPasswordRepository.addPasswordHistory(user, "password")).thenReturn(true);
		
		assertEquals(false, forgotPasswordRepository.addPasswordHistory(user, ""));
		assertEquals(false, forgotPasswordRepository.addPasswordHistory(user, null));
		assertEquals(false, forgotPasswordRepository.addPasswordHistory(null,"password"));
		assertEquals(true, forgotPasswordRepository.addPasswordHistory(user, "password"));
	}
	
	
}
