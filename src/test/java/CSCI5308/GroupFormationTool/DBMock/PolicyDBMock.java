package CSCI5308.GroupFormationTool.DBMock;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.AccessControl.IPolicy;
import CSCI5308.GroupFormationTool.AccessControl.IPolicyRepository;
import CSCI5308.GroupFormationTool.Model.Policy;

public class PolicyDBMock implements IPolicyRepository{

	@Override
	public ArrayList<IPolicy> passwordSPolicyCheck(String password) {
		ArrayList<IPolicy> policies = new ArrayList<IPolicy>();
		IPolicy policy = new Policy();
		policy.setId(0);
		policy.setEnabled(1);
		policy.setSetting("Min length");
		policy.setValue("3");
		policies.add(policy);
		return policies;
	}


	@Override
	public ArrayList<IPolicy> getPolicies() {
		ArrayList<IPolicy> policies = new ArrayList<IPolicy>();
		IPolicy policy = new Policy();
		policy.setId(0);
		policy.setEnabled(1);
		policy.setSetting("Min length");
		policy.setValue("3");
		policies.add(policy);
		return policies;
	}

}
