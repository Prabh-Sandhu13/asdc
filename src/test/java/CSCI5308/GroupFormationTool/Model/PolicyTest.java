package CSCI5308.GroupFormationTool.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.AccessControl.IPolicy;

@SpringBootTest
public class PolicyTest {
	
	@Test
	public void getIdTest() {
		IPolicy policy = new Policy();

		policy.setId(0);
		assertEquals(0,policy.getId());
	}

	@Test
	public void setIdTest() {
		IPolicy policy = new Policy();
		policy.setId(1);
		assertEquals(1,policy.getId());
	}
	
	@Test
	public void getSettingTest() {
		IPolicy policy = new Policy();
		assertNull(policy.getSetting());

		policy.setSetting("Minimum length");
		assertEquals("Minimum length",policy.getSetting());

	}

	@Test
	public void setSettingTest() {
		IPolicy policy = new Policy();
		policy.setSetting("Max length");
		assertEquals("Max length",policy.getSetting());

	}

	@Test
	public void getValueTest() {
		IPolicy policy = new Policy();
		assertNull(policy.getValue());

		policy.setValue("3");
		assertEquals("3",policy.getValue());
	}

	@Test
	public void setValueTest() {
		IPolicy policy = new Policy();
		policy.setValue("4");
		assertEquals("4",policy.getValue());
	}

	@Test
	public void getEnabledTest() {
		IPolicy policy = new Policy();

		policy.setEnabled(0);
		assertEquals(0,policy.getEnabled());
	}

	@Test
	public void setEnabledTest() {
		IPolicy policy = new Policy();
		policy.setEnabled(1);
		assertEquals(1,policy.getEnabled());
	}

}
