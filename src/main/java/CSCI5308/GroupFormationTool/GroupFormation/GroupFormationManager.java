package CSCI5308.GroupFormationTool.GroupFormation;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Question.IQuestion;
import CSCI5308.GroupFormationTool.Question.IQuestionAbstractFactory;
import CSCI5308.GroupFormationTool.Question.QuestionInjector;
import CSCI5308.GroupFormationTool.Survey.IResponse;
import CSCI5308.GroupFormationTool.Survey.ISurvey;
import CSCI5308.GroupFormationTool.Survey.ISurveyAbstractFactory;
import CSCI5308.GroupFormationTool.Survey.SurveyInjector;
import CSCI5308.GroupFormationTool.User.IUser;
import org.apache.commons.text.similarity.LevenshteinDetailedDistance;
import org.apache.commons.text.similarity.LevenshteinResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class GroupFormationManager implements IGroupFormationManager {

    private static final Logger log = LoggerFactory.getLogger(GroupFormationManager.class.getName());
    private IGroupFormationRepository groupFormationRepository;

    @Override
    public TreeMap<Integer, ArrayList<IUser>> getGroupsForCourse(String courseId) {
        log.info("Fetching the groups for the course from the repository");
        groupFormationRepository = GroupFormationInjector.instance().getGroupFormationRepository();
        return groupFormationRepository.getGroupsForCourse(courseId);
    }

    @Override
    public HashMap<Integer, ArrayList<Long>> formGroups(String courseId) {
        IGroupFormationAbstractFactory groupFormationAbstractFactory = GroupFormationInjector.instance().
                getGroupFormationAbstractFactory();
        ISurveyAbstractFactory surveyAbstractFactory = SurveyInjector.instance().getSurveyAbstractFactory();
        IQuestionAbstractFactory questionAbstractFactory = QuestionInjector.instance().getQuestionAbstractFactory();
        ISurvey survey = surveyAbstractFactory.createSurveyInstance();
        ArrayList<IQuestion> questions = survey.getQuestionsForSurvey(courseId);
        ArrayList<Long> userAnsweredSurveyBasedOnCourseId = survey.getUsersWhoTookSurvey(courseId);
        HashMap<Long, IGroupFormula> groupLogic = groupFormationRepository.getGroupFormationLogic(courseId);

        HashMap<Long, HashMap<Long, IResponse>> studentWithQuestionAndAnswer = survey.
                getAllStudentResponses(courseId);

        HashMap<Long, Integer> indexUserIdToIndex = groupFormationAbstractFactory.createIndexUserIdToIndexInstance();
        HashMap<Integer, Long> indexUserIndexToUserID = groupFormationAbstractFactory.
                createIndexUserIndexToUserIdInstance();
        int index = 0;
        for (Long userId : userAnsweredSurveyBasedOnCourseId) {
            indexUserIdToIndex.put(userId, index);
            indexUserIndexToUserID.put(index, userId);
            index++;
        }

        HashMap<Integer, ArrayList<IQuestion>> questionsBasedOnType = groupFormationAbstractFactory.
                createQuestionsBasedOnTypeInstance();

        for (IQuestion question : questions) {
            if (questionsBasedOnType.containsKey(question.getType())) {
                questionsBasedOnType.get(question.getType()).add(question);

            } else {
                ArrayList<IQuestion> eachTypeQuestions = questionAbstractFactory.createQuestionListInstance();
                eachTypeQuestions.add(question);
                questionsBasedOnType.put(question.getType(), eachTypeQuestions);
            }
        }

        HashMap<Long, ArrayList<ArrayList<Double>>> finalMatrices = groupFormationAbstractFactory.
                finalMatricesInstance();

        HashMap<String, HashMap<Integer, Integer>> additionalMappings = groupFormationAbstractFactory.
                additionalMappingsInstance();

        for (Map.Entry<Integer, ArrayList<IQuestion>> entry : questionsBasedOnType.entrySet()) {
            if (entry.getKey() == DomainConstants.freeText) {
                HashMap<Long, ArrayList<ArrayList<Double>>> typeMatrices = formTextMatrixForAllStudents(
                        entry.getValue(), userAnsweredSurveyBasedOnCourseId.size(),
                        studentWithQuestionAndAnswer, groupLogic);
                finalMatrices.put((long) DomainConstants.freeText, AddMatrices(typeMatrices, true,
                        userAnsweredSurveyBasedOnCourseId.size()));
            } else if (entry.getKey() == DomainConstants.numeric) {
                HashMap<Long, ArrayList<ArrayList<Double>>> typeMatrices =
                        formNumericMatrixForAllStudents(
                                entry.getValue(), userAnsweredSurveyBasedOnCourseId.size(),
                                studentWithQuestionAndAnswer, groupLogic);
                finalMatrices.put((long) DomainConstants.numeric, AddMatrices(typeMatrices, true,
                        userAnsweredSurveyBasedOnCourseId.size()));
                additionalMappings = GetAdditonalNumericMappings(entry.getValue(), groupLogic,
                        studentWithQuestionAndAnswer, indexUserIdToIndex);
            } else if (entry.getKey() == DomainConstants.MCQOne) {
                HashMap<Long, ArrayList<ArrayList<Double>>> typeMatrices =
                        formSingleChoiceMatrixForAllStudents(
                                entry.getValue(), userAnsweredSurveyBasedOnCourseId.size(),
                                studentWithQuestionAndAnswer, groupLogic);
                finalMatrices.put((long) DomainConstants.MCQOne, AddMatrices(typeMatrices, true,
                        userAnsweredSurveyBasedOnCourseId.size()));
            } else if (entry.getKey() == DomainConstants.MCQMultiple) {
                HashMap<Long, ArrayList<ArrayList<Double>>> typeMatrices = formMultiChoiceMatrixForAllStudents(
                        entry.getValue(), userAnsweredSurveyBasedOnCourseId.size(), studentWithQuestionAndAnswer,
                        groupLogic);
                finalMatrices.put((long) DomainConstants.MCQMultiple, AddMatrices(typeMatrices, true,
                        userAnsweredSurveyBasedOnCourseId.size()));
            }
        }
        ArrayList<ArrayList<Double>> finalTotalMatrices = AddMatrices(finalMatrices, false,
                userAnsweredSurveyBasedOnCourseId.size());
        Map.Entry<Long, IGroupFormula> entry = groupLogic.entrySet().iterator().next();
        Integer teamSize = entry.getValue().getGroupSize();
        System.out.println(finalTotalMatrices);
        return groupFormationAlgorithm(finalTotalMatrices, additionalMappings, teamSize,
                userAnsweredSurveyBasedOnCourseId.size(), indexUserIndexToUserID);
    }

    @Override
    public void insertUserToGroups(String courseId, HashMap<Integer, ArrayList<Long>> teams) {
        log.info("Inserting into the groups for the course from the repository");
        groupFormationRepository = GroupFormationInjector.instance().getGroupFormationRepository();
        for (Map.Entry<Integer, ArrayList<Long>> team : teams.entrySet()) {
            for (Long student :
                    team.getValue()) {
                groupFormationRepository.insertUserToGroups(team.getKey(), courseId, student);
            }
        }
    }

    @Override
    public void deleteGroups(String courseId) {
        log.info("Deleting the groups for the course from the repository");
        groupFormationRepository = GroupFormationInjector.instance().getGroupFormationRepository();
        groupFormationRepository.deleteGroups(courseId);
    }

    private ArrayList<ArrayList<Double>> AddMatrices(HashMap<Long, ArrayList<ArrayList<Double>>> typeMatrices,
                                                     boolean considerMean, int students) {

        ArrayList<ArrayList<Double>> totalMatrix = new ArrayList<ArrayList<Double>>(students);
        for (int i = 0; i < students; i++) {
            ArrayList<Double> col = new ArrayList<Double>(students);
            for (int j = 0; j < students; j++) {
                col.add(0.0);
            }
            totalMatrix.add(col);
        }
        for (Map.Entry<Long, ArrayList<ArrayList<Double>>> questionMatrix : typeMatrices.entrySet()) {
            for (int row = 0; row < students; row++)
                for (int column = 0; column < students; column++)
                    totalMatrix.get(row).set(column,
                            totalMatrix.get(row).get(column) + questionMatrix.getValue().get(row).get(column));
        }
        if (considerMean) {
            for (int row = 0; row < students; row++)
                for (int column = 0; column < students; column++)
                    totalMatrix.get(row).set(column, totalMatrix.get(row).get(column) / (double) typeMatrices.size());
        }
        return totalMatrix;
    }

    private HashMap<Long, ArrayList<ArrayList<Double>>> formMultiChoiceMatrixForAllStudents(
            ArrayList<IQuestion> questions, int students, HashMap<Long,
            HashMap<Long, IResponse>> studentWithQuestionAndAnswer,
            HashMap<Long, IGroupFormula> groupLogic) {
        IGroupFormationAbstractFactory groupFormationAbstractFactory = GroupFormationInjector.instance()
                .getGroupFormationAbstractFactory();
        HashMap<Long, ArrayList<ArrayList<Double>>> questionMatrix =
                groupFormationAbstractFactory.finalMatricesInstance();
        for (IQuestion question : questions) {
            ArrayList<ArrayList<Double>> matrix = new ArrayList<>(students);

            for (Map.Entry<Long, HashMap<Long, IResponse>> student : studentWithQuestionAndAnswer
                    .entrySet()) {
                ArrayList<Double> row = new ArrayList<Double>(students);
                for (Map.Entry<Long, HashMap<Long, IResponse>> secondStudent : studentWithQuestionAndAnswer
                        .entrySet()) {
                    if (student.getKey() == secondStudent.getKey()) {
                        row.add(-999.9);
                    } else {
                        List<String> studentOptions = student.getValue().get(question.getId())
                                .getOptions();
                        List<String> secondStudentOptions = secondStudent.getValue().get(question.getId())
                                .getOptions();
                        List<String> intersect = studentOptions.stream().filter(secondStudentOptions::contains)
                                .collect(Collectors.toList());
                        double probabiltyValue = (double) (2 * intersect.size())
                                / (double) (studentOptions.size() + secondStudentOptions.size());
                        if (groupLogic.get(question.getId()).getSimilarity() == 1) {
                            row.add(probabiltyValue);
                        } else {
                            row.add(1 - probabiltyValue);
                        }
                    }
                }
                matrix.add(row);
            }
            questionMatrix.put(question.getId(), matrix);
        }
        return questionMatrix;
    }

    private HashMap<String, HashMap<Integer, Integer>> GetAdditonalNumericMappings(
            ArrayList<IQuestion> numericTypeMatrix, HashMap<Long, IGroupFormula> groupLogic,
            HashMap<Long, HashMap<Long, IResponse>> studentWithQuestionAndAnswer,
            HashMap<Long, Integer> indexUserIdToIndex) {

        IGroupFormationAbstractFactory groupFormationAbstractFactory = GroupFormationInjector.instance().
                getGroupFormationAbstractFactory();
        HashMap<String, HashMap<Integer, Integer>> mappings = groupFormationAbstractFactory.additionalMappingsInstance();
        HashMap<Integer, Integer> lessthanXValues = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> greaterthanXValues = new HashMap<Integer, Integer>();
        for (Map.Entry<Long, Integer> user : indexUserIdToIndex.entrySet()) {
            lessthanXValues.put(user.getValue(), 0);
            greaterthanXValues.put(user.getValue(), 0);
        }
        for (IQuestion question : numericTypeMatrix) {
            for (Map.Entry<Long, HashMap<Long, IResponse>> student : studentWithQuestionAndAnswer
                    .entrySet()) {
                Integer studentKey = indexUserIdToIndex.get(student.getKey());
                if (Integer.parseInt(
                        student.getValue().get(question.getId()).getAnswerText()) <
                        groupLogic.get(question.getId()).getLesserThan()) {
                    if (lessthanXValues.containsKey(studentKey)) {
                        lessthanXValues.put(studentKey, lessthanXValues.get(studentKey) + 1);
                    }
                }
                if (Integer.parseInt(student.getValue().get(question.getId()).getAnswerText()) >
                        groupLogic.get(question.getId()).getGreaterThan()) {
                    if (greaterthanXValues.containsKey(studentKey)) {
                        greaterthanXValues.put(studentKey, greaterthanXValues.get(studentKey) + 1);
                    }
                }
            }
        }
        for (Map.Entry<Integer, Integer> lessXvalue : lessthanXValues.entrySet()) {
            double avglessXvalue = lessXvalue.getValue() * 0.1 / (double) (numericTypeMatrix.size());
            if (avglessXvalue > 0.5) {
                lessXvalue.setValue(1);
            } else {
                lessXvalue.setValue(0);
            }
        }
        mappings.put("lessThanX", lessthanXValues);
        for (Map.Entry<Integer, Integer> greaterXvalue : greaterthanXValues.entrySet()) {
            double avggreatXvalue = greaterXvalue.getValue() * 0.1 / (double) (numericTypeMatrix.size());
            if (avggreatXvalue > 0.5) {
                greaterXvalue.setValue(1);
            } else {
                greaterXvalue.setValue(0);
            }
        }
        mappings.put("greaterThanX", greaterthanXValues);
        return mappings;
    }

    private HashMap<Long, ArrayList<ArrayList<Double>>> formNumericMatrixForAllStudents(
            ArrayList<IQuestion> questions, int students, HashMap<Long,
            HashMap<Long, IResponse>> studentWithQuestionAndAnswer, HashMap<Long, IGroupFormula> groupLogic) {

        IGroupFormationAbstractFactory groupFormationAbstractFactory = GroupFormationInjector.instance().
                getGroupFormationAbstractFactory();
        HashMap<Long, ArrayList<ArrayList<Double>>> questionMatrix = groupFormationAbstractFactory.
                finalMatricesInstance();
        for (IQuestion question : questions) {
            ArrayList<ArrayList<Double>> matrix = new ArrayList<>(students);

            for (Map.Entry<Long, HashMap<Long, IResponse>> student : studentWithQuestionAndAnswer
                    .entrySet()) {
                ArrayList<Double> row = new ArrayList<Double>(students);
                for (Map.Entry<Long, HashMap<Long, IResponse>> secondStudent : studentWithQuestionAndAnswer
                        .entrySet()) {

                    if (student.getKey() == secondStudent.getKey()) {
                        row.add(-999.9);
                    } else if (student.getValue().get(question.getId()).getAnswerText().equals(
                            secondStudent.getValue().get(question.getId()).getAnswerText())) {
                        if (groupLogic.get(question.getId()).getSimilarity() == 1) {
                            row.add(0.0);
                        } else {
                            row.add(1.0);
                        }
                    } else {
                        if (groupLogic.get(question.getId()).getSimilarity() == 1) {
                            row.add(1.0);
                        } else {
                            row.add(0.0);
                        }
                    }
                }
                matrix.add(row);
            }
            questionMatrix.put(question.getId(), matrix);
        }
        return questionMatrix;
    }

    private HashMap<Long, ArrayList<ArrayList<Double>>> formTextMatrixForAllStudents(ArrayList<IQuestion> value
            , int students, HashMap<Long,
            HashMap<Long, IResponse>> studentWithQuestionAndAnswer, HashMap<Long, IGroupFormula> groupLogic) {
        IGroupFormationAbstractFactory groupFormationAbstractFactory = GroupFormationInjector.instance().
                getGroupFormationAbstractFactory();
        HashMap<Long, ArrayList<ArrayList<Double>>> questionMatrix = groupFormationAbstractFactory.
                finalMatricesInstance();
        for (IQuestion question : value) {
            ArrayList<ArrayList<Double>> matrix = groupFormationAbstractFactory.getMatrixInstance(students);
            for (Map.Entry<Long, HashMap<Long, IResponse>> student : studentWithQuestionAndAnswer
                    .entrySet()) {
                ArrayList<Double> row = new ArrayList<Double>(students);
                for (Map.Entry<Long, HashMap<Long, IResponse>> secondStudent : studentWithQuestionAndAnswer
                        .entrySet()) {
                    if (student.getKey() == secondStudent.getKey()) {
                        row.add(-999.9);
                    } else {
                        LevenshteinDetailedDistance distance = new LevenshteinDetailedDistance();
                        String studentText = student.getValue().get(question.getId()).getAnswerText();
                        String secondStudentText = secondStudent.getValue().get(question.getId()).getAnswerText();
                        LevenshteinResults calcualtedDistance = distance.apply(studentText, secondStudentText);
                        double probabiltyValue = calcualtedDistance.getDistance() * 1.0 / (double) 10.0;
                        if (groupLogic.get(question.getId()).getMatchWords() > 0) {
                            row.add(probabiltyValue);
                        } else {
                            row.add(1 - probabiltyValue);
                        }
                    }
                }
                matrix.add(row);
            }
            questionMatrix.put(question.getId(), matrix);
        }
        return questionMatrix;
    }

    private HashMap<Integer, ArrayList<Long>> groupFormationAlgorithm(ArrayList<ArrayList<Double>> finalTotalMatrices,
                                                                      HashMap<String, HashMap<Integer, Integer>> additionalMappings,
                                                                      Integer teamSize, int students,
                                                                      HashMap<Integer, Long> indexUserIndexToUserId) {
        HashMap<Integer, ArrayList<Integer>> teams = new HashMap();
        HashMap<Integer, ArrayList<Long>> teamsWithUserId = new HashMap();

        int count = 0;

        HashMap<Integer, Integer> studentLessThanX = new HashMap<>();
        HashMap<Integer, Integer> studentGreaterthanX = new HashMap<>();
        if (additionalMappings.containsKey("lessThanX")) {
            studentLessThanX = additionalMappings.get("lessThanX");
        }
        if (additionalMappings.containsKey("greaterThanX")) {
            studentGreaterthanX = additionalMappings.get("greaterThanX");
        }
        ArrayList<Integer> selected_students = new ArrayList<>();
        for (int x = 0; x < finalTotalMatrices.size(); x++) {
            if (!selected_students.contains(x)) {
                ArrayList<Integer> selected_students_1 = this.generate_team(finalTotalMatrices.get(x), teamSize,
                        studentLessThanX, studentGreaterthanX, true, true);
                for (Integer i : selected_students_1) {
                    studentLessThanX.remove(i);
                    for (int row = 0; row < finalTotalMatrices.get(i).size(); row++) {
                        finalTotalMatrices.get(i).set(row, 999.0);
                        finalTotalMatrices.get(row).set(i, 999.0);
                    }
                }
                selected_students.addAll(selected_students_1);
                teams.put(count, selected_students_1);
            }
            count++;

        }
        for (Map.Entry<Integer, ArrayList<Integer>> team : teams.entrySet()) {
            ArrayList<Long> listTeam = new ArrayList<Long>(teamSize);
            for (Integer id : team.getValue()) {
                listTeam.add(indexUserIndexToUserId.get(id));
            }
            teamsWithUserId.put(team.getKey(), listTeam);
        }
        return teamsWithUserId;
    }

    private ArrayList<Integer> generate_team(ArrayList<Double> arrayList, int team_size,
                                             HashMap<Integer, Integer> studentLessThanX,
                                             HashMap<Integer, Integer> studentGreaterthanX,
                                             Boolean useLessthanX, Boolean useGreaterthanX) {
        HashMap<Integer, Double> map = new HashMap<Integer, Double>();
        for (int i = 0; i < arrayList.size(); ++i) {
            map.put(i, arrayList.get(i));
        }
        HashMap<Integer, Double> sortedValues = sortByValue(map);
        Set<Integer> indices = sortedValues.keySet();
        ArrayList<Integer> top3 = new ArrayList<Integer>();
        Boolean notUsedLessthanX = true;
        Boolean notUsedgreaterthanX = true;
        for (Integer i : indices) {
            if (useGreaterthanX && notUsedgreaterthanX) {
                if (studentGreaterthanX.containsKey(i)) {
                    if (studentGreaterthanX.get(i) == 0) {
                        top3.add(i);
                        notUsedgreaterthanX = false;
                    }

                }

            }
            if (useLessthanX && notUsedLessthanX) {
                if (studentLessThanX.containsKey(i)) {
                    if (studentLessThanX.get(i) == 1) {
                        top3.add(i);
                        notUsedLessthanX = false;
                    }
                }
            }
            if (top3.size() == 2) {
                break;
            } else if (!(useLessthanX && useGreaterthanX)) {
                if (top3.size() == 1) {
                    break;
                }
            }
        }
        for (Integer i : indices) {
            if (top3.size() == team_size) {
                break;
            } else if (!top3.contains(i) && sortedValues.get(i) != 999.0) {
                top3.add(i);
            }
        }
        return top3;
    }

    public HashMap<Integer, Double> sortByValue(HashMap<Integer, Double> hm) {
        List<Map.Entry<Integer, Double>> list = new LinkedList<Map.Entry<Integer, Double>>(hm.entrySet());
        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));
        HashMap<Integer, Double> sortedHashMap = new LinkedHashMap<Integer, Double>();
        for (Map.Entry<Integer, Double> item : list) {
            sortedHashMap.put(item.getKey(), item.getValue());
        }
        return sortedHashMap;
    }

    private HashMap<Long, ArrayList<ArrayList<Double>>> formSingleChoiceMatrixForAllStudents(
            ArrayList<IQuestion> questions, int students, HashMap<Long,
            HashMap<Long, IResponse>> studentWithQuestionAndAnswer, HashMap<Long, IGroupFormula> groupLogic) {

        IGroupFormationAbstractFactory groupFormationAbstractFactory = GroupFormationInjector.instance().
                getGroupFormationAbstractFactory();
        HashMap<Long, ArrayList<ArrayList<Double>>> questionMatrix = groupFormationAbstractFactory.
                finalMatricesInstance();
        for (IQuestion question : questions) {
            ArrayList<ArrayList<Double>> matrix = new ArrayList<>(students);

            for (Map.Entry<Long, HashMap<Long, IResponse>> student : studentWithQuestionAndAnswer
                    .entrySet()) {
                ArrayList<Double> row = new ArrayList<Double>(students);
                for (Map.Entry<Long, HashMap<Long, IResponse>> secondStudent : studentWithQuestionAndAnswer
                        .entrySet()) {

                    if (student.getKey() == secondStudent.getKey()) {
                        row.add(-999.9);
                    } else if (student.getValue().get(question.getId()).getOptions().get(0).equals(
                            secondStudent.getValue().get(question.getId()).getOptions().get(0))) {
                        if (groupLogic.get(question.getId()).getSimilarity() == 1) {
                            row.add(0.0);
                        } else {
                            row.add(1.0);
                        }
                    } else {
                        if (groupLogic.get(question.getId()).getSimilarity() == 1) {
                            row.add(1.0);
                        } else {
                            row.add(0.0);
                        }
                    }
                }
                matrix.add(row);
            }
            questionMatrix.put(question.getId(), matrix);
        }
        return questionMatrix;
    }

}
