package CSCI5308.GroupFormationTool.Controller;

import CSCI5308.GroupFormationTool.AccessControl.IUserController;
import CSCI5308.GroupFormationTool.AccessControl.IUserService;
import CSCI5308.GroupFormationTool.Model.User;
import CSCI5308.GroupFormationTool.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements IUserController {

	@Override
	public boolean createUser() {
		// TODO Auto-generated method stub
		return false;
	}

}
