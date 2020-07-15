package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.User.IUser;

import java.util.ArrayList;

public interface IResponseRepository {
    IUser getResponseUser(String emailId);

    long getResponseOptionId(long questionId, String optionText);

    boolean storeResponses(ArrayList<IResponse> responseList);
}
