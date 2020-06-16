package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;

public interface IPolicyRepository {

	public ArrayList<IPolicy> passwordSPolicyCheck(String password);
	public ArrayList<IPolicy> getPolicies();

}
