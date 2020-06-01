package CSCI5308.GroupFormationTool.SecurityTest;

import CSCI5308.GroupFormationTool.AccessControl.IPasswordEncryptor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BCryptEncryptionTest implements IPasswordEncryptor {

	@Override
	public String encoder(String rawPassword) {
		return "encrypted";
	}

	@Override
	public boolean passwordMatch(String rawPassword, String encryptedPassword) {
		if (null == rawPassword || rawPassword.isEmpty()) {
			return false;
		}
		if (null == encryptedPassword || encryptedPassword.isEmpty()) {
			return false;
		}
		return encryptedPassword.equals("encrypted");
	}

	@Test
	void encryptPassword() {

		String password = "padmesh1234";

		String encryptedPassword = encoder(password);

		assertEquals(true, passwordMatch(password, encryptedPassword));

	}
}
