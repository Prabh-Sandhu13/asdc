package CSCI5308.GroupFormationTool.Security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.Model.User;

public class CustomAuthenticationService implements AuthenticationManager {
	private static final String Admin_banner_id = "B00000000";

	private Authentication checkUser(String password, IUser user, Authentication authentication)
			throws AuthenticationException {
		IPasswordEncryptor passwordEncryptor = Injector.instance().getPasswordEncryptor();

		if (passwordEncryptor.passwordMatch(password, user.getPassword())) {

			// Grant ADMIN rights system-wide, this is used to protect controller mappings.
			List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();

			if (user.getBannerId().toUpperCase().equals(Admin_banner_id)) {
				rights.add(new SimpleGrantedAuthority("ADMIN"));

			} else {
				rights.add(new SimpleGrantedAuthority("USER"));

			}
			// Return valid authentication token.
			UsernamePasswordAuthenticationToken token;
			token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
					authentication.getCredentials(), rights);
			return token;
		} else {
			throw new BadCredentialsException("1000");
		}
	}

	// Authenticate against our database using the input email ID and password.
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String emailId = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();
		IUserRepository userRepository = Injector.instance().getUserRepository();

		IUser user = new User();
		user.setEmailId(emailId);
		try {
			user = userRepository.getUserByEmailId(user);
		} catch (Exception e) {
			throw new AuthenticationServiceException("1000");
		}
		if (user != null) {
			return checkUser(password, user, authentication);
		} else {
			throw new BadCredentialsException("1001");
		}
	}
}
