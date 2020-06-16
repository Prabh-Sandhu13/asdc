package CSCI5308.GroupFormationTool.Service;

import CSCI5308.GroupFormationTool.AccessControl.IQuestion;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.DBMock.QuestionDBMock;
import CSCI5308.GroupFormationTool.Model.Question;
import CSCI5308.GroupFormationTool.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QuestionServiceTest {

    QuestionDBMock questionDBMock = new QuestionDBMock();
    IQuestion question = new Question();
    IUser user = new User();

    @Test
    void createQuestionTest() {

        question.setId(1);
        user.setEmailId("padmeshdonthu@gmail.com");
        question.setInstructor(user);
        question.setText("sample");
        question.setTitle("title text");
        question.setType(1);

        assertEquals(1, questionDBMock.createQuestion(question));

    }

    @Test
    void getQuestionByIdTest() {

        long questionId = 1;

        assertEquals("Sample question", questionDBMock.getQuestionById(questionId).getText());

    }

    @Test
    void getQuestionListForInstructorTest() {

        String emailId = "padmeshdonthu@gmail.com";

        assertEquals(1, questionDBMock.getQuestionListForInstructor(emailId).size());
    }

    @Test
    void getOptionsForQuestionTest() {

        long questionId = 1;

        assertEquals(1, questionDBMock.getOptionsForTheQuestion(questionId).size());

    }

}
