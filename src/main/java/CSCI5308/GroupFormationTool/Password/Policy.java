package CSCI5308.GroupFormationTool.Password;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;

import java.util.ArrayList;

public class Policy implements IPolicy {

    private int id;

    private String setting;

    private String value;

    private int enabled;

    private IPolicyRepository policyRepository;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getSetting() {
        return setting;
    }

    @Override
    public void setSetting(String setting) {
        this.setting = setting;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int getEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }


    @Override
    public String passwordSPolicyCheck(String password) {
        policyRepository = Injector.instance().getPolicyRepository();
        ArrayList<IPolicy> policies = policyRepository.passwordSPolicyCheck(password);
        return checkPasswordSecurity(password, policies);
    }

    @Override
    public ArrayList<IPolicy> getPolicies() {
        policyRepository = Injector.instance().getPolicyRepository();
        ArrayList<IPolicy> policies = policyRepository.getPolicies();
        return policies;
    }

    private String checkPasswordSecurity(String password, ArrayList<IPolicy> policies) {
        int passwordSettingEnabled = 1;
        String errorMessage = null;
        int upperCaseCharacters = 0;
        int lowerCaseCharacters = 0;
        int digits = 0;
        int specialCharacters = 0;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                upperCaseCharacters++;
            } else if (ch >= 'a' && ch <= 'z') {
                lowerCaseCharacters++;
            } else if (ch >= '0' && ch <= '9') {
                digits++;
            } else {
                specialCharacters++;
            }
        }
        for (int counter = 0; counter < policies.size(); counter++) {
            IPolicy policy = policies.get(counter);
            if (errorMessage != null) {
                break;
            }
            int id = policy.getId();
            String val = policy.getValue();
            int enabled = policy.getEnabled();
            if (enabled == passwordSettingEnabled) {
                switch (id) {
                    case 0:
                        if (password.length() < Integer.parseInt(val)) {
                            errorMessage = DomainConstants.passwordMinimumLength + val;
                        }
                        break;
                    case 1:
                        if (password.length() > Integer.parseInt(val)) {
                            errorMessage = DomainConstants.passwordMaximumLength + val;
                        }
                        break;
                    case 2:
                        if (upperCaseCharacters < Integer.parseInt(val)) {
                            errorMessage = DomainConstants.passwordUppercaseMinimumLength + val;
                        }
                        break;
                    case 3:
                        if (lowerCaseCharacters < Integer.parseInt(val)) {
                            errorMessage = DomainConstants.passwordLowercaseMinimumLength + val;
                        }
                        break;
                    case 4:
                        if (specialCharacters < Integer.parseInt(val)) {
                            errorMessage = DomainConstants.passwordSpecialSymbolsMinimumLength + val;
                        }
                        break;
                    case 5:
                        for (int i = 0; i < val.length(); i++) {
                            if (password != null && password.indexOf(val.charAt(i)) >= 0) {
                                errorMessage = "" + val + DomainConstants.passwordCharactersNotAllowed;
                                break;
                            }
                        }
                    default:
                }
            }
        }
        return errorMessage;
    }

}
