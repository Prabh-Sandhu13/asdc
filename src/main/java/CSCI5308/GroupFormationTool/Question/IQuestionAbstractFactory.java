package CSCI5308.GroupFormationTool.Question;

import java.util.ArrayList;

public interface IQuestionAbstractFactory {

    IQuestion createQuestionInstance();
    ArrayList<IQuestion> createQuestionListInstance();
    IChoice createChoiceInstance();
    ArrayList<IChoice> createChoiceListInstance();
}
