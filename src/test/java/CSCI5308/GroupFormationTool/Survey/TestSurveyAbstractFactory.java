package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;

public class TestSurveyAbstractFactory implements ITestSurveyAbstractFactory {
    @Override
    public ISurvey createSurveyInstance() {
        return new Survey();
    }

    @Override
    public SurveyRepository createSurveyRepositoryMock() {
        return mock(SurveyRepository.class);
    }

    @Override
    public ResponseRepository createResponseRepositoryMock() {
        return mock(ResponseRepository.class);
    }

    @Override
    public IResponse createResponseInstance() {
        return new Response();
    }

    @Override
    public ResponseDBMock createResponseDBMockInstance() {
        return new ResponseDBMock();
    }

    @Override
    public List<String> createOptionListInstance() {
        return new ArrayList<String>();
    }

    @Override
    public ArrayList<IResponse> createResponseListInstance() {
        return new ArrayList<IResponse>();
    }

    @Override
    public Map<String, String> createMapResponse() {
        return new HashMap<String, String>();
    }
}
