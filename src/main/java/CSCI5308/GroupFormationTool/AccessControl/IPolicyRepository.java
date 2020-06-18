package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;

public interface IPolicyRepository {

    ArrayList<IPolicy> passwordSPolicyCheck(String password);

    ArrayList<IPolicy> getPolicies();

}
