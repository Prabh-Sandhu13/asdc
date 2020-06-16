package CSCI5308.GroupFormationTool.Service;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IPolicy;
import CSCI5308.GroupFormationTool.AccessControl.IPolicyRepository;
import CSCI5308.GroupFormationTool.AccessControl.IPolicyService;

public class PolicyService implements IPolicyService {

	private IPolicyRepository policyRepository;


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
				// Minimum Length policy
				case 0:
					if (password.length() < Integer.parseInt(val)) {
						errorMessage = "Minimum length of password should be " + val;
					}
					break;
				// Maximum Length policy
				case 1:
					if (password.length() > Integer.parseInt(val)) {
						errorMessage = "Maximum length of password should be " + val;
					}
					break;
				// Minimum number of uppercase characters
				case 2:
					if (upperCaseCharacters < Integer.parseInt(val)) {
						errorMessage = "Minimum number of uppercase characters in password should be " + val;
					}
					break;
				// Minimum number of lowercase characters
				case 3:
					if (lowerCaseCharacters < Integer.parseInt(val)) {
						errorMessage = "Minimum number of lowercase characters in password should be " + val;
					}
					break;
				// Minimum number of symbols or special characters
				case 4:
					if (specialCharacters < Integer.parseInt(val)) {
						errorMessage = "Minimum number of symbols or special characters in password should be " + val;
					}
					break;
				// A set of characters that are not allowed
				case 5:
					if (password != null && password.contains(val)) {
						errorMessage = "" + val + " not allowed in password ";
					}
					break;
				default:
				}
			}
		}
		return errorMessage;
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
	
}
