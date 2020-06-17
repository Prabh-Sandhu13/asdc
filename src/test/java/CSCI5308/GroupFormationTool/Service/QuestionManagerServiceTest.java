package CSCI5308.GroupFormationTool.Service;

import CSCI5308.GroupFormationTool.AccessControl.IChoice;
import CSCI5308.GroupFormationTool.AccessControl.IQuestion;
import CSCI5308.GroupFormationTool.DomainConstants;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Model.Choice;
import CSCI5308.GroupFormationTool.Model.Question;
import CSCI5308.GroupFormationTool.Model.User;
import CSCI5308.GroupFormationTool.Repository.QuestionManagerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QuestionManagerServiceTest {

    public QuestionManagerService questionManagerService;
    public QuestionManagerRepository questionManagerRepository;

    @BeforeEach
    public void init() {
        questionManagerRepository = mock(QuestionManagerRepository.class);
        questionManagerService = new QuestionManagerService();
        Injector.instance().setQuestionManagerRepository(questionManagerRepository);
    }

    @Test
    void getQuestionListForInstructorTest() {
        IQuestion question = new Question();
        ArrayList<IChoice> choices = new ArrayList<>();
        ArrayList<IQuestion> questions = new ArrayList<>();

        IChoice choice = new Choice();
        choice.setText("Amateur");
        choice.setValue(1);
        choices.add(choice);

        choice = new Choice();
        choice.setText("Beginner");
        choice.setValue(2);
        choices.add(choice);

        User user = new User();
        user.setEmailId("padmeshdonthu@gmail.com");

        question.setCreatedDate(new Date(System.currentTimeMillis()));
        question.setId(1);
        question.setInstructor(user);
        question.setText("Spring text");
        question.setTitle("Spring title");
        question.setType(DomainConstants.MCQOne);
        question.setChoices(choices);
        questions.add(question);

        question = new Question();
        question.setCreatedDate(new Date(System.currentTimeMillis()));
        question.setId(2);
        question.setInstructor(user);
        question.setText("Sample text");
        question.setTitle("Sample title");
        question.setType(DomainConstants.numeric);
        question.setChoices(null);
        questions.add(question);

        when(questionManagerRepository.getQuestionListForInstructor(user.getEmailId())).thenReturn(questions);
        assertFalse(questionManagerService.getQuestionListForInstructor(user.getEmailId()).isEmpty());
        assertTrue(questionManagerService.getQuestionListForInstructor(user.getEmailId()).size() == 2);
    }

    @Test
    void createQuestionTest() {
        IQuestion question = new Question();

        User user = new User();
        user.setEmailId("padmeshdonthu@gmail.com");

        question.setCreatedDate(new Date(System.currentTimeMillis()));
        question.setId(1);
        question.setInstructor(user);
        question.setText("Spring text");
        question.setTitle("Spring title");
        question.setType(DomainConstants.MCQOne);

        ArrayList<String> optionText = new ArrayList<>();
        optionText.add("Test");
        optionText.add("Sample");

        ArrayList<String> optionValue = new ArrayList<>();
        optionValue.add("1");
        optionValue.add("2");

        when(questionManagerRepository.createQuestion(question)).thenReturn((long) 1);
        assertFalse(questionManagerService.createQuestion(question, optionText, optionValue) == 0);
        assertTrue(questionManagerService.createQuestion(question, optionText, optionValue) == 1);

        when(questionManagerRepository.createQuestion(question)).thenReturn(DomainConstants.invalidData);
        assertFalse(questionManagerService.createQuestion(question, new ArrayList<>(), optionValue) == 1);
        assertTrue(questionManagerService.createQuestion(question, optionText, optionValue)
                == DomainConstants.invalidData);

        question.setType(DomainConstants.numeric);
        when(questionManagerRepository.createQuestion(question)).thenReturn((long) 1);
        assertFalse(questionManagerService.createQuestion(question, new ArrayList<>(), new ArrayList<>()) == 0);
        assertTrue(questionManagerService.createQuestion(question, new ArrayList<>(), new ArrayList<>()) == 1);

        question.setText("");
        when(questionManagerRepository.createQuestion(question)).thenReturn(DomainConstants.invalidData);
        assertFalse(questionManagerService.createQuestion(question,
                new ArrayList<>(), new ArrayList<>()) == 1);
        assertTrue(questionManagerService.createQuestion(question,
                new ArrayList<>(), new ArrayList<>()) == DomainConstants.invalidData);
    }

    @Test
    void getQuestionByIdTest() {
        long questionId = 1;
        Question question = new Question();
        ArrayList<IChoice> choices = new ArrayList<>();

        IChoice choice = new Choice();
        choice.setText("Amateur");
        choice.setValue(1);
        choices.add(choice);

        choice = new Choice();
        choice.setText("Beginner");
        choice.setValue(2);
        choices.add(choice);

        User user = new User();
        user.setEmailId("padmeshdonthu@gmail.com");

        question.setCreatedDate(new Date(System.currentTimeMillis()));
        question.setId(questionId);
        question.setInstructor(user);
        question.setText("Spring text");
        question.setTitle("Spring title");
        question.setType(DomainConstants.MCQOne);

        when(questionManagerRepository.getQuestionById(questionId)).thenReturn(question);
        when(questionManagerRepository.getOptionsForTheQuestion(questionId)).thenReturn(choices);
        assertFalse(questionManagerService.getQuestionById(questionId) == null);
        assertTrue(questionManagerService.getQuestionById(questionId).getText().equals("Spring text"));

    }

    @Test
    void deleteQuestionTest() {
        long questionId = 1;
        when(questionManagerRepository.deleteQuestion(questionId)).thenReturn(true);
        assertTrue(questionManagerService.deleteQuestion(questionId));
    }

    @Test
    void getSortedQuestionListForInstructorTest() {
        String sortBy = "title";
        IQuestion question = new Question();
        ArrayList<IChoice> choices = new ArrayList<>();
        ArrayList<IQuestion> questions = new ArrayList<>();

        IChoice choice = new Choice();
        choice.setText("Amateur");
        choice.setValue(1);
        choices.add(choice);

        choice = new Choice();
        choice.setText("Beginner");
        choice.setValue(2);
        choices.add(choice);

        User user = new User();
        user.setEmailId("padmeshdonthu@gmail.com");

        question = new Question();
        question.setCreatedDate(new Date(System.currentTimeMillis()));
        question.setId(2);
        question.setInstructor(user);
        question.setText("Sample text");
        question.setTitle("Sample title");
        question.setType(DomainConstants.numeric);
        question.setChoices(null);
        questions.add(question);

        question.setCreatedDate(new Date(System.currentTimeMillis()));
        question.setId(1);
        question.setInstructor(user);
        question.setText("Spring text");
        question.setTitle("Spring title");
        question.setType(DomainConstants.MCQOne);
        question.setChoices(choices);
        questions.add(question);

        when(questionManagerRepository.getSortedQuestionListForInstructor(sortBy, user.getEmailId())).
                thenReturn(questions);
        assertFalse(questionManagerService.getSortedQuestionListForInstructor(sortBy, user.getEmailId()).
                isEmpty());
        assertTrue(questionManagerService.getSortedQuestionListForInstructor(sortBy, user.getEmailId()).
                size() == 2);

    }
}