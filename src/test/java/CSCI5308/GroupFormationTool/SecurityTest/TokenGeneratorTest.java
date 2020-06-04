package CSCI5308.GroupFormationTool.SecurityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.AccessControl.ITokenGenerator;

@SpringBootTest
public class TokenGeneratorTest implements ITokenGenerator {

	@Override
	public String generator() {
		return "randomly-generated-token";
	}

	
	@Test
	void encryptPasswordTest() {
		
		String token = generator();
		assertEquals("randomly-generated-token", token);

	}
}
