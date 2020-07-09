package CSCI5308.GroupFormationTool.Question;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class QuestionAbstractFactoryTest implements IQuestionAbstractFactoryTest {

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

    @Override
    public ChoiceDBMock createChoiceDBMock() {
        return new ChoiceDBMock();
    }

    @Override
    public QuestionDBMock createQuestionDBMock() {
        return new QuestionDBMock();
    }

    @Override
    public QuestionAdminRepository createQuestionAdminRepositoryMock() {
        return mock(QuestionAdminRepository.class);
    }

    @Override
    public QuestionManagerRepository createQuestionManagerRepositoryMock() {
        return mock(QuestionManagerRepository.class);
    }

}
