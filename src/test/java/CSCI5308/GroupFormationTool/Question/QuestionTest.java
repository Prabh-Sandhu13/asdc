package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class QuestionTest {

    public QuestionAdminRepository questionAdminRepository;
    public QuestionManagerRepository questionManagerRepository;

    @BeforeEach
    public void init() {
        questionAdminRepository = mock(QuestionAdminRepository.class);
        questionManagerRepository = mock(QuestionManagerRepository.class);
        Injector.instance().setQuestionAdminRepository(questionAdminRepository);
        Injector.instance().setQuestionManagerRepository(questionManagerRepository);
    }

    public IQuestion createDefaultQuestion() {
        QuestionDBMock questionDBMock = new QuestionDBMock();
        IQuestion question = loadQuestion(questionDBMock);
        return question;
    }

    public IQuestion loadQuestion(QuestionDBMock questionDBMock) {
        IQuestion question = new Question();
        question = questionDBMock.loadQuestion(question);
        return question;
    }

    @Test
    public void getIdTest() {
        IQuestion question = createDefaultQuestion();
        assertEquals(1, question.getId());
    }

    @Test
    public void setIdTest() {
        IQuestion question = new Question();
        question.setId(2);
        assertEquals(2, question.getId());
    }

    @Test
    public void getInstructorTest() {
        IQuestion question = createDefaultQuestion();
        assertEquals("Test", question.getInstructor().getFirstName());
    }

    @Test
    public void setInstructorTest() {
        IQuestion question = new Question();
        question.setInstructor(new User());
        assertEquals(-1, question.getInstructor().getId());
    }

    @Test
    public void getTitleTest() {
        IQuestion question = createDefaultQuestion();
        assertEquals("Sample", question.getTitle());
    }

    @Test
    public void setTitleTest() {
        IQuestion question = new Question();
        question.setTitle("New Title");
        assertEquals("New Title", question.getTitle());
    }

    @Test
    public void getTextTest() {
        IQuestion question = createDefaultQuestion();
        assertEquals("Sample question", question.getText());
    }

    @Test
    public void setTextTest() {
        IQuestion question = new Question();
        question.setText("New Text");
        assertEquals("New Text", question.getText());
    }

    @Test
    public void getTypeTest() {
        IQuestion question = createDefaultQuestion();
        assertEquals(1, question.getType());
    }

    @Test
    public void setTypeTest() {
        IQuestion question = new Question();
        question.setType(2);
        assertEquals(2, question.getType());
    }

    @Test
    public void getCreatedDateTest() {
        IQuestion question = createDefaultQuestion();
        assertEquals("1969-12-31", question.getCreatedDate().toString());
    }

    @Test
    public void setCreatedDateTest() {
        IQuestion question = new Question();
        Date date = new Date(0);
        question.setCreatedDate(date);
        assertEquals("1969-12-31", question.getCreatedDate().toString());
    }

    @Test
    public void getChoicesTest() {
        IQuestion question = createDefaultQuestion();
        assertEquals(1, question.getChoices().size());
    }

    @Test
    public void setChoicesTest() {
        IQuestion question = new Question();
        ArrayList<IChoice> choices = new ArrayList<>();
        IChoice choice = new Choice();
        choice.setText("sample");
        choice.setValue(1);
        choices.add(choice);
        question.setChoices(choices);
        assertEquals(1, question.getChoices().size());
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
        assertFalse(question.createQuestion(optionText, optionValue) == 0);
        assertTrue(question.createQuestion(optionText, optionValue) == 1);
        optionText.add("Sample");
        optionValue.add("3");
        assertFalse(question.createQuestion(optionText, optionValue) == 0);
        assertTrue(question.createQuestion(optionText, optionValue) == 1);
        when(questionManagerRepository.createQuestion(question)).thenReturn(DomainConstants.invalidData);
        assertFalse(question.createQuestion(new ArrayList<>(), optionValue) == 1);
        assertTrue(question.createQuestion(optionText, optionValue)
                == DomainConstants.invalidData);
        question.setType(DomainConstants.numeric);
        when(questionManagerRepository.createQuestion(question)).thenReturn((long) 1);
        assertFalse(question.createQuestion(new ArrayList<>(), new ArrayList<>()) == 0);
        assertTrue(question.createQuestion(new ArrayList<>(), new ArrayList<>()) == 1);
        question.setText("");
        when(questionManagerRepository.createQuestion(question)).thenReturn(DomainConstants.invalidData);
        assertFalse(question.createQuestion(
                new ArrayList<>(), new ArrayList<>()) == 1);
        assertTrue(question.createQuestion(
                new ArrayList<>(), new ArrayList<>()) == DomainConstants.invalidData);
    }

    @Test
    void deleteQuestionTest() {
        long questionId = 1;
        IQuestion question = new Question();
        when(questionManagerRepository.deleteQuestion(questionId)).thenReturn(true);
        assertTrue(question.deleteQuestion(questionId));
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
        assertTrue(question.getQuestionListForInstructor(user.getEmailId()) != null);
        assertTrue(question.getQuestionListForInstructor(user.getEmailId()).size() == 2);

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
        assertTrue(question.getQuestionById(questionId).getText().equals("Spring text"));
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
        assertFalse(question.getSortedQuestionListForInstructor(sortByField, user.getEmailId()).
                isEmpty());
        assertTrue(question.getSortedQuestionListForInstructor(sortByField, user.getEmailId()).
                size() == 2);
    }
}
