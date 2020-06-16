package CSCI5308.GroupFormationTool.RepositoryTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.AccessControl.IQuestion;
import CSCI5308.GroupFormationTool.DBMock.QuestionDBMock;
import CSCI5308.GroupFormationTool.Model.Question;
import CSCI5308.GroupFormationTool.Repository.QuestionManagerRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QuestionRepositoryTest {

	private QuestionManagerRepository questionManagerRepository;
	QuestionDBMock questionDBMock = new QuestionDBMock();

	@Test
	void createQuestionTest() {

		questionManagerRepository = mock(QuestionManagerRepository.class);

		IQuestion question = new Question();

		when(questionManagerRepository.createQuestion(question)).thenReturn(questionDBMock.createQuestion(question));

		assertEquals(1, questionManagerRepository.createQuestion(question));

		when(questionManagerRepository.createQuestion(question)).thenReturn(new Question().getId());

		assertEquals(-1, questionManagerRepository.createQuestion(question));

	}

	@Test
	void getOptionsForTheQuestionTest() {

		questionManagerRepository = mock(QuestionManagerRepository.class);

		long questionId = 1;

		when(questionManagerRepository.getOptionsForTheQuestion(questionId)).thenReturn(new ArrayList<>());

		assertEquals(0, questionManagerRepository.getOptionsForTheQuestion(questionId).size());

		when(questionManagerRepository.getOptionsForTheQuestion(questionId))
				.thenReturn(questionDBMock.getOptionsForTheQuestion(questionId));

		assertEquals(1, questionManagerRepository.getOptionsForTheQuestion(questionId).size());

	}

	@Test
	void getQuestionByIdTest() {
		questionManagerRepository = mock(QuestionManagerRepository.class);

		long questionId = 1;

		when(questionManagerRepository.getQuestionById(questionId)).thenReturn(new Question());

		assertEquals(-1, questionManagerRepository.getQuestionById(questionId).getId());

		when(questionManagerRepository.getQuestionById(questionId))
				.thenReturn(questionDBMock.getQuestionById(questionId));

		assertEquals("Sample", questionManagerRepository.getQuestionById(questionId).getTitle());

	}

	@Test
	void getQuestionListForInstructorTest() {

		questionManagerRepository = mock(QuestionManagerRepository.class);

		String emailId = "padmeshdonthu@gmail.com";

		when(questionManagerRepository.getQuestionListForInstructor(emailId)).thenReturn(null);

		assertEquals(null, questionManagerRepository.getQuestionListForInstructor(emailId));

		when(questionManagerRepository.getQuestionListForInstructor(emailId))
				.thenReturn(questionDBMock.getQuestionListForInstructor(emailId));

		assertEquals(1, questionManagerRepository.getQuestionListForInstructor(emailId).size());

	}
}
