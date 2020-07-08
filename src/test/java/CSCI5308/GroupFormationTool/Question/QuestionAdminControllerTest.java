package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.sql.Date;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(QuestionAdminController.class)
public class QuestionAdminControllerTest {

    public QuestionAdminRepository questionAdminRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        questionAdminRepository = mock(QuestionAdminRepository.class);
        Injector.instance().setQuestionAdminRepository(questionAdminRepository);
    }

    @Test
    void questionListTest() throws Exception {
        String emailId = "padmeshdonthu@gmail.com";
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

        when(questionAdminRepository.getQuestionListForInstructor(emailId)).thenReturn(questions);

        this.mockMvc.perform(get("/questionManager/questionManager"))
                .andExpect(status().isOk())
                .andExpect(view().name("question/questionManager"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void viewQuestion() throws Exception {
        long questionId = 1;
        IQuestion question = new Question();
        question.setCreatedDate(new Date(System.currentTimeMillis()));
        question.setId(questionId);
        question.setInstructor(null);
        question.setText("Sample text");
        question.setTitle("Sample title");
        question.setType(DomainConstants.numeric);
        question.setChoices(null);

        when(questionAdminRepository.getQuestionById(questionId)).thenReturn(question);

        this.mockMvc.perform(get("/questionManager/viewQuestion")
                .param("questionId", String.valueOf(questionId)))
                .andExpect(status().isOk())
                .andExpect(view().name("question/viewQuestion"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void sortQuestion() throws Exception {
        String sortField = "title";
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
        when(questionAdminRepository.getSortedQuestionListForInstructor(sortField, user.getEmailId())).
                thenReturn(questions);

        this.mockMvc.perform(get("/questionManager/sortQuestion")
                .param("sortby", sortField))
                .andExpect(status().isOk())
                .andExpect(view().name("question/questionManager"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
