package CSCI5308.GroupFormationTool.Security;

import java.util.UUID;

import CSCI5308.GroupFormationTool.AccessControl.ITokenGenerator;

public class TokenGenerator implements ITokenGenerator{

	@Override
	public String generator() {

		return UUID.randomUUID().toString();
	}

}
