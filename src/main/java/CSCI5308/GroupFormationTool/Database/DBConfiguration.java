package CSCI5308.GroupFormationTool.Database;

import org.springframework.stereotype.Service;

@Service
public class DBConfiguration implements IDBConfiguration {
	private static final String URL = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_22_DEVINT?useSSL=false"
			+"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	private static final String USER = "CSCI5308_22_DEVINT_USER";
	private static final String PASSWORD = "CSCI5308_22_DEVINT_22320";

	@Override
	public String getDBUserName() {
		return USER;
	}

	@Override
	public String getDBPassword() {
		return PASSWORD;
	}

	@Override
	public String getDBURL() {
		return URL;
	}
}
