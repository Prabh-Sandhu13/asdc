package CSCI5308.GroupFormationTool.AccessControl;

public interface IQuestionManagerRepository {
    long createQuestion(IQuestion question);
    boolean deleteQuestion(long questionId);
}
