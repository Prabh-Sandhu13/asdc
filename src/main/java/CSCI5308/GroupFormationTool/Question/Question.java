package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.User.IUser;

import java.sql.Date;
import java.util.*;

public class Question implements IQuestion {

    private long id;

    private IUser instructor;

    private String title;

    private String text;

    private int type;

    private Date createdDate;

    private ArrayList<IChoice> choices;

    private IQuestionManagerRepository questionManagerRepository;

    private IQuestionAdminRepository questionAdminRepository;

    private IQuestionAbstractFactory questionAbstractFactory = Injector.instance().getQuestionAbstractFactory();
    public Question() {
        this.id = -1;
        this.instructor = null;
        this.title = null;
        this.text = null;
        this.type = -1;
        this.createdDate = null;
        this.choices = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public IUser getInstructor() {
        return instructor;
    }

    public void setInstructor(IUser instructor) {
        this.instructor = instructor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public ArrayList<IChoice> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<IChoice> choices) {
        this.choices = choices;
    }

    @Override
    public long createQuestion(List<String> optionText, List<String> optionValue) {
        int type = this.type;
        Set<String> optionTextSet = new HashSet<>();
        Set<String> optionValueSet = new HashSet<>();

        if (checkIfInvalid(optionText, optionValue)) {
            return DomainConstants.invalidData;
        } else {
            ArrayList<IChoice> choices = questionAbstractFactory.createChoiceListInstance();
            for (String text : optionText) {
                optionTextSet.add(text);
            }
            for (String value : optionValue) {
                optionValueSet.add(value);
            }

            if (type == DomainConstants.MCQMultiple || type == DomainConstants.MCQOne) {

                if (optionTextSet.size() == optionValueSet.size()) {
                    Iterator<String> optionTextIterator = optionTextSet.iterator();
                    Iterator<String> optionValueIterator = optionValueSet.iterator();

                    while (optionTextIterator.hasNext() && optionValueIterator.hasNext()) {
                        IChoice choice = questionAbstractFactory.createChoiceInstance();
                        choice.setText(optionTextIterator.next());
                        choice.setValue(Integer.parseInt(optionValueIterator.next()));
                        choices.add(choice);
                    }
                } else {
                    for (int i = 0; i < optionText.size(); i++) {
                        IChoice choice = questionAbstractFactory.createChoiceInstance();
                        choice.setText(optionText.get(i));
                        choice.setValue(Integer.parseInt(optionValue.get(i)));
                        choices.add(choice);
                    }
                }
                this.setChoices(choices);
            } else {
                this.setChoices(null);
            }
            questionManagerRepository = Injector.instance().getQuestionManagerRepository();
            return questionManagerRepository.createQuestion(this);
        }
    }

    @Override
    public boolean deleteQuestion(long questionId) {
        questionManagerRepository = Injector.instance().getQuestionManagerRepository();
        return questionManagerRepository.deleteQuestion(questionId);
    }

    @Override
    public ArrayList<IQuestion> getQuestionListForInstructor(String emailId) {
        questionAdminRepository = Injector.instance().getQuestionAdminRepository();
        return questionAdminRepository.getQuestionListForInstructor(emailId);
    }

    @Override
    public ArrayList<IQuestion> getSortedQuestionListForInstructor(String emailId, String sortBy) {
        questionAdminRepository = Injector.instance().getQuestionAdminRepository();
        return questionAdminRepository.getSortedQuestionListForInstructor(emailId, sortBy);
    }

    @Override
    public IQuestion getQuestionById(long questionId) {
        questionAdminRepository = Injector.instance().getQuestionAdminRepository();
        IQuestion question = questionAdminRepository.getQuestionById(questionId);
        ArrayList<IChoice> choiceList = null;

        if (question.getType() == DomainConstants.MCQMultiple || question.getType() == DomainConstants.MCQOne) {
            choiceList = questionAdminRepository.getOptionsForTheQuestion(questionId);
        }
        question.setChoices(choiceList);
        return question;
    }

    private boolean checkIfInvalid(List<String> optionText, List<String> optionValue) {

        if (this.title == null || this.title.isEmpty() || this.text == null || this.text.isEmpty()) {
            return true;
        }
        if (this.type == DomainConstants.MCQMultiple || this.type == DomainConstants.MCQOne) {
            if (optionText == null || optionText.isEmpty() || optionText.contains("") || optionValue == null
                    || optionValue.isEmpty() || optionValue.contains("")) {
                return true;
            }
        }
        return false;
    }

}
