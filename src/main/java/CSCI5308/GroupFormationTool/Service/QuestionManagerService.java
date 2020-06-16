package CSCI5308.GroupFormationTool.Service;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IChoice;
import CSCI5308.GroupFormationTool.AccessControl.IQuestion;
import CSCI5308.GroupFormationTool.AccessControl.IQuestionManagerRepository;
import CSCI5308.GroupFormationTool.AccessControl.IQuestionManagerService;
import CSCI5308.GroupFormationTool.Model.Choice;

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
	public long createQuestion(IQuestion question, List<String> optionText, List<String> optionValue) {

		int type = question.getType();

		if (checkIfInvalid(question.getTitle(), question.getText(), type, optionText, optionValue)) {
			return invalidData;
		} else {

			try {

				ArrayList<IChoice> choices = new ArrayList<>();

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
			} catch (Exception ex) {
				return invalidData;
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

	public boolean deleteQuestion(long questionId) {

		questionManagerRepository = Injector.instance().getQuestionManagerRepository();

		return questionManagerRepository.deleteQuestion(questionId);

	}

	@Override
	public ArrayList<IQuestion> getSortedQuestionListForInstructor(String emailId, String sortBy) {

		questionManagerRepository = Injector.instance().getQuestionManagerRepository();

		return questionManagerRepository.getSortedQuestionListForInstructor(emailId, sortBy);

	}
}
