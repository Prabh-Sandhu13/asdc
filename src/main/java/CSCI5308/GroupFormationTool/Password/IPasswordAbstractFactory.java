package CSCI5308.GroupFormationTool.Password;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public interface IPasswordAbstractFactory {

    IForgotPasswordManager createForgotPasswordManagerInstance();

    IForgotPasswordRepository createForgotPasswordRepositoryInstance();

    ITokenGenerator createTokenGeneratorInstance();

    IPasswordHistoryManager createPasswordHistoryManagerInstance();

    IPasswordHistoryRepository createPasswordHistoryRepositoryInstance();

    IPolicy createPolicyInstance();

    IPolicyRepository createPolicyRepository();

    ArrayList<IPolicy> createPolicyListInstance();

    ArrayList<String> createPasswordList();

    Date createDateInstance();

    SimpleDateFormat createSimpleDateFormatInstance();
}
