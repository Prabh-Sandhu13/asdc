package CSCI5308.GroupFormationTool.Question;

import java.util.ArrayList;

public class QuestionAbstractFactory implements IQuestionAbstractFactory {

    @Override
    public IQuestion createQuestionInstance() {
        return new Question();
    }

    @Override
    public ArrayList<IQuestion> createQuestionListInstance() {
        return new ArrayList<IQuestion>();
    }

    @Override
    public IChoice createChoiceInstance() {
        return new Choice();
    }

    @Override
    public ArrayList<IChoice> createChoiceListInstance() {
        return new ArrayList<IChoice>();
    }
}
