package CSCI5308.GroupFormationTool.Password;

import java.util.ArrayList;

public interface IPolicyService {

    String passwordSPolicyCheck(String password);

    ArrayList<IPolicy> getPolicies();

}
