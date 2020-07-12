package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;

public class SurveyAbstractFactory implements ISurveyAbstractFactory {
    @Override
    public ISurvey createSurveyInstance() {
        return new Survey();
    }

    @Override
    public IResponse createResponseInstance() {
        return new Response();
    }

    @Override
    public ArrayList<IResponse> createResponseListInstance() {
        return new ArrayList<IResponse>();
    }

    @Override
    public SurveyRepository createSurveyRepositoryInstance() {
        return new SurveyRepository();
    }

    @Override
    public ResponseRepository createResponseRepositoryInstance() {
        return new ResponseRepository();
    }
}
