package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.User.IUser;

public interface IResponseRepository {
	public IUser getResponseUser(String emailId);
	public long getResponseOptionId(long questionId, String optionText);
}
