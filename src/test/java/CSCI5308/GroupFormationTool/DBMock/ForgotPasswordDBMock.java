package CSCI5308.GroupFormationTool.DBMock;

import CSCI5308.GroupFormationTool.Model.User;

public class ForgotPasswordDBMock {
    public boolean addToken(User user, String token) {
    	if(user == null || token.equals("") || token.equals(null)) {
    	return false;
    	}
    	else {
    		return true;
    	}
    }
    public String getToken(User user) {
    	if(user == null) {
    		return null;
    	}
    	else {
    		return "token";
    	}
    }
    public boolean updateToken(User user, String token) {
    	if(user == null || token.equals("") || token.equals(null)) {
    	return false;
    	}
    	else {
    		return true;
    	}
    }
    public boolean updatePassword(User user, String password) {
    	if(user == null || password.equals("") || password.equals(null)) {
    	user.setPassword(password);
    	return false;
    	}
    	else {
    		return true;
    	}
    }
    public boolean deleteToken(User user, String token) {
    	
    	if(user == null || token.equals("") || token.equals(null)) {
    	return false;
    	}
    	else {
    		return true;
    	}
    }
    
    public User getUserId(User user) {
    	if(user != null && user.getId() == 123) {
    		return user;
    	}
    	else {
    		return null;
    	}
    }
    
    public User getEmailByToken(User user,String token) {
		
    	if(user != null && user.getEmailId().equalsIgnoreCase("haard.shah@dal.ca")) {
    		return user;
    	}
    	else {
		return null;
    	}
    }


}
