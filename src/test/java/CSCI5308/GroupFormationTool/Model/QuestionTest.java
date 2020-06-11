package CSCI5308.GroupFormationTool.Model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.AccessControl.IQuestion;
import CSCI5308.GroupFormationTool.DBMock.QuestionDBMock;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;

@SpringBootTest
public class QuestionTest {

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
		Calendar myCal = Calendar.getInstance();
		myCal.set(2020, 06, 11);
		System.out.println(myCal.toString());
		System.out.println(question.getCreatedDate().toString());
		assertEquals(myCal.getTime().toString(), question.getCreatedDate().getTime().toString());

	}

	@Test
	public void setCreatedDateTest() {
		IQuestion question = new Question();
		question.setCreatedDate(null);
		assertEquals(null, question.getCreatedDate());

	}

}
