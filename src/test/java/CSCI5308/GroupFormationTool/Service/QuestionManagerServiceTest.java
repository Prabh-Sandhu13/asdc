package CSCI5308.GroupFormationTool.Service;

import CSCI5308.GroupFormationTool.AccessControl.IQuestion;
import CSCI5308.GroupFormationTool.DomainConstants;
import CSCI5308.GroupFormationTool.Injector;
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
        optionText.add("Sample");
        optionValue.add("3");
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
    void deleteQuestionTest() {
        long questionId = 1;
        when(questionManagerRepository.deleteQuestion(questionId)).thenReturn(true);
        assertTrue(questionManagerService.deleteQuestion(questionId));
    }
}