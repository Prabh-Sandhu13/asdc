package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;

public interface IPolicyService {

	public String passwordSPolicyCheck(String password);
	public ArrayList<IPolicy> getPolicies();

}
