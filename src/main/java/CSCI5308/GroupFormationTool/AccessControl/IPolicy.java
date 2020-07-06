package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;

public interface IPolicy {

    int getId();

    void setId(int id);

    String getSetting();

    void setSetting(String setting);

    String getValue();

    void setValue(String value);

    int getEnabled();

    void setEnabled(int enabled);

	String passwordSPolicyCheck(String password);

	ArrayList<IPolicy> getPolicies();

}
