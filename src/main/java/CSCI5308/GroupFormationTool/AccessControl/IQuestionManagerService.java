package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;
import java.util.List;

public interface IQuestionManagerService {

    long createQuestion(IQuestion question, List<String> optionText, List<String> optionValue);
    
    boolean deleteQuestion(long questionId);


}
