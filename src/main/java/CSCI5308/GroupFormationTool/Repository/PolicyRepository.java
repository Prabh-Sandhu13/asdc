package CSCI5308.GroupFormationTool.Repository;

import CSCI5308.GroupFormationTool.AccessControl.IPolicy;
import CSCI5308.GroupFormationTool.AccessControl.IPolicyRepository;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Model.Policy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PolicyRepository implements IPolicyRepository {

    @Override
    public ArrayList<IPolicy> passwordSPolicyCheck(String password) {
        ArrayList<IPolicy> policies = new ArrayList<IPolicy>();
        StoredProcedure storedProcedure = null;
        IPolicy policy = null;
        try {
            storedProcedure = new StoredProcedure("sp_getPasswordConfigSettings");
            ResultSet results = storedProcedure.executeWithResults();

            if (results != null) {
                while (results.next()) {
                    {
                        policy = new Policy();
                        policy.setId(results.getInt("pSetting_id"));
                        policy.setSetting(results.getString("pSetting"));
                        policy.setValue(results.getString("pSetting_value"));
                        policy.setEnabled(results.getInt("is_enabled"));
                        policies.add(policy);
                    }
                }
            }

        } catch (SQLException ex) {

        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return policies;
    }

    @Override
    public ArrayList<IPolicy> getPolicies() {
        ArrayList<IPolicy> policies = new ArrayList<IPolicy>();
        StoredProcedure storedProcedure = null;
        IPolicy policy = null;
        try {
            storedProcedure = new StoredProcedure("sp_getPolicies");
            ResultSet results = storedProcedure.executeWithResults();

            if (results != null) {
                while (results.next()) {
                    {
                        policy = new Policy();
                        policy.setId(results.getInt("pSetting_id"));
                        policy.setSetting(results.getString("pSetting"));
                        policy.setValue(results.getString("pSetting_value"));
                        policies.add(policy);
                    }
                }
            }

        } catch (SQLException ex) {

        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return policies;
    }
}
