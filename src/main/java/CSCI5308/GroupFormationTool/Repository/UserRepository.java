package CSCI5308.GroupFormationTool.Repository;

import CSCI5308.GroupFormationTool.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements IUserRepository {

	@Override
	public boolean createUser(User user) {

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
	public User getUserByEmailId(User user) {

		User userByEmailId = null;
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
	public User getUserByBannerId(User user) {
		StoredProcedure storedProcedure = null;
		User userByBannerId = null;
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

}
