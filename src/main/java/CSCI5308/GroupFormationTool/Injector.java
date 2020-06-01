package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.AccessControl.ICourseRepository;
import CSCI5308.GroupFormationTool.AccessControl.ICourseService;
import CSCI5308.GroupFormationTool.AccessControl.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserCoursesRepository;
import CSCI5308.GroupFormationTool.AccessControl.IUserCoursesService;
import CSCI5308.GroupFormationTool.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.AccessControl.IUserService;
import CSCI5308.GroupFormationTool.Database.DBConfiguration;
import CSCI5308.GroupFormationTool.Database.IDBConfiguration;
import CSCI5308.GroupFormationTool.Model.User;
import CSCI5308.GroupFormationTool.Repository.CourseRepository;
import CSCI5308.GroupFormationTool.Repository.UserCoursesRepository;
import CSCI5308.GroupFormationTool.Repository.UserRepository;
import CSCI5308.GroupFormationTool.Security.BCryptEncryption;
import CSCI5308.GroupFormationTool.Service.CourseService;
import CSCI5308.GroupFormationTool.Service.UserCoursesService;
import CSCI5308.GroupFormationTool.Service.UserService;

// Important for Dependency Injection
public class Injector {

	private static Injector instance = null;

	private IDBConfiguration dbConfiguration;
	private IUserRepository userRepository;
	private IUserService userService;
	private IPasswordEncryptor passwordEncryptor;
	private ICourseService courseService;
	private ICourseRepository courseRepository;
	private IUserCoursesRepository userCoursesRepository;
	private IUserCoursesService userCoursesService;

	private Injector() {

		dbConfiguration = new DBConfiguration();
		userRepository = new UserRepository();
		userService = new UserService();
		passwordEncryptor = new BCryptEncryption();
		courseService = new CourseService();
		courseRepository = new CourseRepository();
		userCoursesRepository = new UserCoursesRepository();
		userCoursesService = new UserCoursesService();
	}

	public IUserCoursesRepository getUserCoursesRepository() {
		return userCoursesRepository;
	}

	public IUserCoursesService getUserCoursesService() {
		return userCoursesService;
	}

	public static Injector instance() {

		if (instance == null) {
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

	public ICourseService getCourseService() {
		return courseService;
	}

	public ICourseRepository getCourseRepository() {
		return courseRepository;
	}
}
