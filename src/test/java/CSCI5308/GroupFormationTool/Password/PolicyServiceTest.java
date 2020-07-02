package CSCI5308.GroupFormationTool.Password;

import CSCI5308.GroupFormationTool.Password.IPolicy;
import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Password.Policy;
import CSCI5308.GroupFormationTool.Password.PolicyRepository;
import CSCI5308.GroupFormationTool.Password.PolicyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PolicyServiceTest {

    private PolicyService policyService;
    private PolicyRepository policyRepository;

    @BeforeEach
    public void init() {
        policyService = new PolicyService();
        policyRepository = mock(PolicyRepository.class);
        Injector.instance().setPolicyRepository(policyRepository);
    }

    @Test
    public void passwordPolicyCheckTest() {
        String password = "Padmes$1";
        ArrayList<IPolicy> policies = new ArrayList<>();
        IPolicy policy = new Policy();
        policy.setId(0);
        policy.setEnabled(1);
        policy.setSetting("Minimum length");
        policy.setValue("3");
        policies.add(policy);
        policy = new Policy();
        policy.setId(1);
        policy.setEnabled(1);
        policy.setSetting("Maximum length");
        policy.setValue("9");
        policies.add(policy);
        policy = new Policy();
        policy.setId(2);
        policy.setEnabled(1);
        policy.setSetting("Minimum number of uppercase characters");
        policy.setValue("1");
        policies.add(policy);
        policy = new Policy();
        policy.setId(3);
        policy.setEnabled(1);
        policy.setSetting("Minimum number of lowercase characters");
        policy.setValue("1");
        policies.add(policy);
        policy = new Policy();
        policy.setId(4);
        policy.setEnabled(1);
        policy.setSetting("Minimum number of special characters or symbols");
        policy.setValue("1");
        policies.add(policy);
        policy = new Policy();
        policy.setId(5);
        policy.setEnabled(1);
        policy.setSetting("A set of characters that are not allowed");
        policy.setValue("#");
        policies.add(policy);
        when(policyRepository.passwordSPolicyCheck(password)).thenReturn(policies);
        assertTrue(policyService.passwordSPolicyCheck(password) == null);
        String errorMessage = "Minimum length of password should be 3";
        password = "pa";
        when(policyRepository.passwordSPolicyCheck(password)).thenReturn(policies);
        assertTrue(policyService.passwordSPolicyCheck(password).equals(errorMessage));
        errorMessage = "Maximum length of password should be 9";
        password = "padmeshA12333444";
        when(policyRepository.passwordSPolicyCheck(password)).thenReturn(policies);
        assertTrue(policyService.passwordSPolicyCheck(password).equals(errorMessage));
        errorMessage = "Minimum number of uppercase characters in password should be 1";
        password = "padmesh1";
        when(policyRepository.passwordSPolicyCheck(password)).thenReturn(policies);
        assertTrue(policyService.passwordSPolicyCheck(password).equals(errorMessage));
        errorMessage = "Minimum number of lowercase characters in password should be 1";
        password = "PADMESH1";
        when(policyRepository.passwordSPolicyCheck(password)).thenReturn(policies);
        assertTrue(policyService.passwordSPolicyCheck(password).equals(errorMessage));
        errorMessage = "Minimum number of symbols or special characters in password should be 1";
        password = "PaDMESH1";
        when(policyRepository.passwordSPolicyCheck(password)).thenReturn(policies);
        assertTrue(policyService.passwordSPolicyCheck(password).equals(errorMessage));
        errorMessage = "# not allowed in password ";
        password = "PaDMESH1#";
        when(policyRepository.passwordSPolicyCheck(password)).thenReturn(policies);
        assertTrue(policyService.passwordSPolicyCheck(password).equals(errorMessage));
    }

    @Test
    public void getPoliciesTest() {
        ArrayList<IPolicy> policies = new ArrayList<>();
        IPolicy policy = new Policy();
        policy.setId(0);
        policy.setEnabled(1);
        policy.setSetting("Min length");
        policy.setValue("3");
        policies.add(policy);
        when(policyRepository.getPolicies()).thenReturn(policies);
        assertTrue(policyService.getPolicies().size() == 1);
        when(policyRepository.getPolicies()).thenReturn(null);
        assertFalse(policyService.getPolicies() instanceof ArrayList);
    }
}
