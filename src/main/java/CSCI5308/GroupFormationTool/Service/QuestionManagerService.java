package CSCI5308.GroupFormationTool.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IChoice;
import CSCI5308.GroupFormationTool.AccessControl.IQuestion;
import CSCI5308.GroupFormationTool.AccessControl.IQuestionManagerRepository;
import CSCI5308.GroupFormationTool.AccessControl.IQuestionManagerService;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.Model.Choice;
import CSCI5308.GroupFormationTool.Model.Question;
import CSCI5308.GroupFormationTool.Model.User;

public class QuestionManagerService implements IQuestionManagerService {

	private final static int MCQOne = 2;
	private final static int MCQMultiple = 3;
	private final static long invalidData = 0;

	IQuestionManagerRepository questionManagerRepository;

	@Override
	public ArrayList<IQuestion> getQuestionListForInstructor(String emailId) {

		questionManagerRepository = Injector.instance().getQuestionManagerRepository();

		return questionManagerRepository.getQuestionListForInstructor(emailId);

	}

	@Override
	public long createQuestion(String title, String text, int type, List<String> optionText, List<String> optionValue,
			String emailId) {

		if (checkIfInvalid(title, text, type, optionText, optionValue)) {
			return invalidData;
		} else {
			IQuestion question = new Question();

			IUser user = new User();

			ArrayList<IChoice> choices = new ArrayList<>();

			question.setText(text);
			question.setTitle(title);
			question.setType(type);
			user.setEmailId(emailId);
			question.setInstructor(user);

			if (type == MCQMultiple || type == MCQOne) {

				for (int i = 0; i < optionText.size(); i++) {

					IChoice choice = new Choice();
					choice.setText(optionText.get(i));
					choice.setValue(Integer.parseInt(optionValue.get(i)));

					choices.add(choice);

				}
				question.setChoices(choices);

			} else {
				question.setChoices(null);
			}

			questionManagerRepository = Injector.instance().getQuestionManagerRepository();

			return questionManagerRepository.createQuestion(question);

		}
	}

	private boolean checkIfInvalid(String title, String text, int type, List<String> optionText,
			List<String> optionValue) {

		if (title == null || title.isEmpty() || text == null || text.isEmpty()) {
			return true;
		}
		if (type == MCQMultiple || type == MCQOne) {
			if (optionText == null || optionText.isEmpty() || optionText.contains("") || optionValue == null
					|| optionValue.isEmpty() || optionValue.contains("")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public IQuestion getQuestionById(long questionId) {

		questionManagerRepository = Injector.instance().getQuestionManagerRepository();

		IQuestion question = questionManagerRepository.getQuestionById(questionId);

		ArrayList<IChoice> choiceList = null;

		if (question.getType() == MCQMultiple || question.getType() == MCQOne) {
			choiceList = questionManagerRepository.getOptionsForTheQuestion(questionId);
		}

		question.setChoices(choiceList);

		return question;

	}

}
