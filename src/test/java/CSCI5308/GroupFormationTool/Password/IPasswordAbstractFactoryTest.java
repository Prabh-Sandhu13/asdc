package CSCI5308.GroupFormationTool.Password;

import java.util.ArrayList;

public interface IPasswordAbstractFactoryTest {

    PasswordHistoryManager createPasswordHistoryManagerMock();

    PolicyRepository createPolicyRepositoryMock();

    IPolicy createPolicyInstance();

    ForgotPasswordDBMock createForgotPasswordDBMock();

    ForgotPasswordRepository createForgotPasswordRepositoryMock();

    Policy createPolicyMock();

    IForgotPasswordManager createForgotPasswordManagerInstance();

    PasswordHistoryRepository createPasswordHistoryRepositoryMock();

    ArrayList<IPolicy> createPolicyListInstance();

    PasswordHistoryManager createPasswordHistoryManagerInstance();

}
