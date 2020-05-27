package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.AccessControl.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.AccessControl.IUserService;
import CSCI5308.GroupFormationTool.Database.DBConfiguration;
import CSCI5308.GroupFormationTool.Database.IDBConfiguration;
import CSCI5308.GroupFormationTool.Repository.UserRepository;
import CSCI5308.GroupFormationTool.Security.BCryptEncryption;
import CSCI5308.GroupFormationTool.Service.UserService;

public class Injector {

	private static Injector instance = null;

	private IDBConfiguration dbConfiguration;
	private IUserRepository userRepository;
	private IUserService userService;
	private IPasswordEncryptor passwordEncryptor;
	
	private Injector() {

		dbConfiguration = new DBConfiguration();
		userRepository = new UserRepository();
		userService = new UserService();
		passwordEncryptor = new BCryptEncryption();
	}

	public static Injector instance() {

		if(instance==null) {
			instance = new Injector();
		}
		return instance;
	}

	public IPasswordEncryptor getPasswordEncryptor() {
		return passwordEncryptor;
	}

	public IUserRepository getUserRepository() {
		return userRepository;
	}

	public IDBConfiguration getDbConfiguration() {
		return dbConfiguration;
	}

	public IUserService getUserService() {
		return userService;
	}

}
