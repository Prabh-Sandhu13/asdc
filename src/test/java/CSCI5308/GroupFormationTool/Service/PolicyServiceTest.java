package CSCI5308.GroupFormationTool.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.AccessControl.IPolicy;
import CSCI5308.GroupFormationTool.AccessControl.IPolicyRepository;
import CSCI5308.GroupFormationTool.AccessControl.IPolicyService;
import CSCI5308.GroupFormationTool.DBMock.PolicyDBMock;
import CSCI5308.GroupFormationTool.Repository.PolicyRepository;

@SpringBootTest
public class PolicyServiceTest {
	private IPolicyRepository policyRepo;
	@Test
	public void passwordPolicyCheckTest() {
		
		policyRepo = mock(PolicyRepository.class);
		String password = "12";
		IPolicyRepository policyMock = new PolicyDBMock();
		ArrayList<IPolicy> policies = policyMock.getPolicies();
		IPolicyService policyService = new PolicyService();
		
		when(policyRepo.passwordSPolicyCheck(password)).thenReturn(policies);
		
		assertNotEquals(null, policyService.passwordSPolicyCheck(password));
		
		assertEquals("Minimum length of password should be 3", policyService.passwordSPolicyCheck("12"));
		
	}

}
