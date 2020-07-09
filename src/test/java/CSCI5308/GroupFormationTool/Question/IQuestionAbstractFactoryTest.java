package CSCI5308.GroupFormationTool.Question;

import java.util.ArrayList;

public interface IQuestionAbstractFactoryTest {
    IQuestion createQuestionInstance();

    ArrayList<IQuestion> createQuestionListInstance();

    IChoice createChoiceInstance();

    ArrayList<IChoice> createChoiceListInstance();

    ChoiceDBMock createChoiceDBMock();

    QuestionDBMock createQuestionDBMock();

    QuestionAdminRepository createQuestionAdminRepositoryMock();

    QuestionManagerRepository createQuestionManagerRepositoryMock();

}
