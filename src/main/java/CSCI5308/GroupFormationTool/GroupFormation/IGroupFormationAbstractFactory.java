package CSCI5308.GroupFormationTool.GroupFormation;

import CSCI5308.GroupFormationTool.Question.IQuestion;
import CSCI5308.GroupFormationTool.User.IUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public interface IGroupFormationAbstractFactory {

    IGroupFormationManager createGroupFormationManagerInstance();

    GroupFormationRepository createGroupFormationRepositoryInstance();

    TreeMap<Integer, ArrayList<IUser>> createGroupsForCourseInstance();

    HashMap<Long, Integer> createIndexUserIdToIndexInstance();

    HashMap<Integer, Long> createIndexUserIndexToUserIdInstance();

    HashMap<Integer, ArrayList<IQuestion>> createQuestionsBasedOnTypeInstance();

    HashMap<Long, ArrayList<ArrayList<Double>>> finalMatricesInstance();

    HashMap<String, HashMap<Integer, Integer>> additionalMappingsInstance();

    ArrayList<ArrayList<Double>> getMatrixInstance(int size);

    IGroupFormula createGroupFormulaInstance();

    public HashMap<Long, IGroupFormula> createGroupLogicInstance();
}
