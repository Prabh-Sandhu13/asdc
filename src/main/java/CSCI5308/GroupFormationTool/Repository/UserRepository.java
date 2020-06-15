package CSCI5308.GroupFormationTool.Repository;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IPolicy;
import CSCI5308.GroupFormationTool.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Model.Policy;
import CSCI5308.GroupFormationTool.Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository implements IUserRepository {

	@Override
	public boolean createUser(IUser user) {

		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("sp_create_user(?,?,?,?,?)");
			storedProcedure.setInputStringParameter(1, user.getBannerId());
			storedProcedure.setInputStringParameter(2, user.getFirstName());
			storedProcedure.setInputStringParameter(3, user.getLastName());
			storedProcedure.setInputStringParameter(4, user.getEmailId());
			storedProcedure.setInputStringParameter(5, user.getPassword());

			storedProcedure.execute();

		} catch (SQLException ex) {

			return false;
		} finally {
			if (storedProcedure != null) {
				storedProcedure.removeConnections();
			}
		}
		return true;
	}

	@Override
	public IUser getUserByEmailId(IUser user) {

		IUser userByEmailId = null;
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("sp_getUserByEmailId(?)");
			storedProcedure.setInputStringParameter(1, user.getEmailId());
			ResultSet results = storedProcedure.executeWithResults();

			if (results != null) {

				while (results.next()) {
					{
						userByEmailId = new User();
						userByEmailId.setBannerId(results.getString("banner_id"));
						userByEmailId.setEmailId(results.getString("email"));
						userByEmailId.setFirstName(results.getString("first_name"));
						userByEmailId.setLastName(results.getString("last_name"));
						userByEmailId.setPassword(results.getString("password"));
					}
				}
			}

		} catch (SQLException ex) {
			
			System.out.println(ex.getMessage());

		} finally {
			if (storedProcedure != null) {
				storedProcedure.removeConnections();
			}
		}
		return userByEmailId;

	}

	@Override
	public IUser getUserByBannerId(IUser user) {
		StoredProcedure storedProcedure = null;
		IUser userByBannerId = null;
		try {
			storedProcedure = new StoredProcedure("sp_getUserByBannerId(?)");
			storedProcedure.setInputStringParameter(1, user.getBannerId());
			ResultSet results = storedProcedure.executeWithResults();

			if (results != null) {
				while (results.next()) {
					{
						userByBannerId = new User();
						userByBannerId.setBannerId(results.getString("banner_id"));
						userByBannerId.setEmailId(results.getString("email"));
						userByBannerId.setFirstName(results.getString("first_name"));
						userByBannerId.setLastName(results.getString("last_name"));
					}
				}
			}

		} catch (SQLException ex) {

		} finally {
			if (storedProcedure != null) {
				storedProcedure.removeConnections();
			}
		}
		return userByBannerId;
	}

	@Override
	public IUser getAdminDetails() {
		StoredProcedure storedProcedure = null;
		IUser adminDetails = null;
		try {
			storedProcedure = new StoredProcedure("sp_getAdminDetails");
			ResultSet results = storedProcedure.executeWithResults();

			if (results != null) {
				while (results.next()) {
					{
						adminDetails = new User();
						adminDetails.setBannerId(results.getString("banner_id"));
						adminDetails.setEmailId(results.getString("email"));
						adminDetails.setFirstName(results.getString("first_name"));
						adminDetails.setLastName(results.getString("last_name"));
					}
				}
			}

		} catch (SQLException ex) {

		} finally {
			if (storedProcedure != null) {
				storedProcedure.removeConnections();
			}
		}
		return adminDetails;
	}

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
						System.out.println(results.getInt("pSetting_id"));
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
}
