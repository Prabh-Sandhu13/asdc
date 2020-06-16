package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;

public interface IPolicyService {

    String passwordSPolicyCheck(String password);

    ArrayList<IPolicy> getPolicies();

}
