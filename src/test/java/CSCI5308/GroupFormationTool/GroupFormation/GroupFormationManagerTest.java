package CSCI5308.GroupFormationTool.GroupFormation;

import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.TestsInjector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class GroupFormationManagerTest {

    private ITestGroupFormationAbstractFactory testGroupFormationAbstractFactory = TestsInjector.instance().
            getGroupFormationAbstractFactoryTest();

    private GroupFormationRepository groupFormationRepository;

    private IGroupFormationManager groupFormationManager;

    @BeforeEach
    void init() {
        groupFormationRepository = testGroupFormationAbstractFactory.createGroupFormationRepositoryMock();
        Injector.instance().setGroupFormationRepository(groupFormationRepository);
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
