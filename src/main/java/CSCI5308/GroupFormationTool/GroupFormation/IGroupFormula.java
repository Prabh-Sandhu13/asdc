package CSCI5308.GroupFormationTool.GroupFormation;

public interface IGroupFormula {

    public long getQuestionId();

    public void setQuestionId(long questionId);

    public int getSimilarity();

    public void setSimilarity(int similarity);

    public int getMatchWords();

    public void setMatchWords(int matchWords);

    public int getGreaterThan();

    public void setGreaterThan(int greaterThan);

    public int getLesserThan();

    public void setLesserThan(int lesserThan);

    public int getGroupSize();

    public void setGroupSize(int groupSize);
}
