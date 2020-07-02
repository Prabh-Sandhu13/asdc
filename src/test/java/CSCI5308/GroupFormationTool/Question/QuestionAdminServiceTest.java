package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestionAdminServiceTest {

    public QuestionAdminService questionAdminService;
    public QuestionAdminRepository questionAdminRepository;

    @BeforeEach
    public void init() {
        questionAdminRepository = mock(QuestionAdminRepository.class);
        questionAdminService = new QuestionAdminService();
        Injector.instance().setQuestionAdminRepository(questionAdminRepository);
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
        when(questionAdminRepository.getQuestionListForInstructor(user.getEmailId())).thenReturn(questions);
        assertTrue(questionAdminService.getQuestionListForInstructor(user.getEmailId()) != null);
        assertTrue(questionAdminService.getQuestionListForInstructor(user.getEmailId()).size() == 2);

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
        when(questionAdminRepository.getQuestionById(questionId)).thenReturn(question);
        when(questionAdminRepository.getOptionsForTheQuestion(questionId)).thenReturn(choices);
        assertFalse(questionAdminRepository.getQuestionById(questionId) == null);
        assertTrue(questionAdminService.getQuestionById(questionId).getText().equals("Spring text"));
    }

    @Test
    void getSortedQuestionListForInstructorTest() {
        String sortByField = "title";
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
        when(questionAdminRepository.getSortedQuestionListForInstructor(sortByField, user.getEmailId())).
                thenReturn(questions);
        assertFalse(questionAdminService.getSortedQuestionListForInstructor(sortByField, user.getEmailId()).
                isEmpty());
        assertTrue(questionAdminService.getSortedQuestionListForInstructor(sortByField, user.getEmailId()).
                size() == 2);
    }

}
