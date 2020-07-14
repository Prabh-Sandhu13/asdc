package CSCI5308.GroupFormationTool.GroupFormation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class GroupFormationManagerTest {

    private ITestGroupFormationAbstractFactory testGroupFormationAbstractFactory = TestGroupFormationInjector.
            instance().getGroupFormationAbstractFactory();

    private GroupFormationRepository groupFormationRepository;

    private IGroupFormationManager groupFormationManager;

    @BeforeEach
    void init() {
        groupFormationRepository = testGroupFormationAbstractFactory.createGroupFormationRepositoryMock();
        GroupFormationInjector.instance().setGroupFormationRepository(groupFormationRepository);
        groupFormationManager = testGroupFormationAbstractFactory.createGroupFormationManagerInstance();
    }

    @Test
    void getGroupsForCourseTest() {
        String courseId = "CSCI 5308";
        when(groupFormationRepository.getGroupsForCourse(courseId)).
                thenReturn(testGroupFormationAbstractFactory.createGroupsInstance());
        assertTrue(groupFormationManager.getGroupsForCourse(courseId).size() == 0);
    }
}
