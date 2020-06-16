package CSCI5308.GroupFormationTool.RepositoryTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.AccessControl.IPolicy;
import CSCI5308.GroupFormationTool.AccessControl.IPolicyRepository;
import CSCI5308.GroupFormationTool.DBMock.PolicyDBMock;
import CSCI5308.GroupFormationTool.Repository.PolicyRepository;



@SpringBootTest
public class PolicyRepositoryTest {
	
	private IPolicyRepository policyRepo;
	IPolicyRepository policyMock = new PolicyDBMock();
	@Test
	public void passwordSPolicyCheckTest() {
		policyRepo = mock(PolicyRepository.class);

		when(policyRepo.passwordSPolicyCheck("password"))
				.thenReturn(new ArrayList<IPolicy>());

		assertEquals(0, policyRepo.passwordSPolicyCheck("password").size());
		
		when(policyRepo.passwordSPolicyCheck("password"))
		.thenReturn(policyMock.getPolicies());
		assertEquals(1, policyRepo.passwordSPolicyCheck("password").size());
		
	}
	
	@Test
	public void getPoliciesTest() {
		policyRepo = mock(PolicyRepository.class);

		when(policyRepo.getPolicies())
				.thenReturn(new ArrayList<IPolicy>());

		assertEquals(0, policyRepo.getPolicies().size());
		
		when(policyRepo.getPolicies())
		.thenReturn(policyMock.getPolicies());
		assertEquals(1, policyRepo.getPolicies().size());
	}

}
