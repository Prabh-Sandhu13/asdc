package CSCI5308.GroupFormationTool.Security;

import CSCI5308.GroupFormationTool.User.IUser;
import CSCI5308.GroupFormationTool.User.IUserAbstractFactory;
import CSCI5308.GroupFormationTool.User.IUserRepository;
import CSCI5308.GroupFormationTool.Common.FactoryProducer;
import CSCI5308.GroupFormationTool.Common.Injector;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class CustomAuthenticationManager implements AuthenticationManager {

    private static final String Admin_banner_id = "B00000000";
    private IUserAbstractFactory userAbstractFactory = FactoryProducer.
            getFactory().createUserAbstractFactory();

    private Authentication checkUser(String password, IUser user, Authentication authentication)
            throws AuthenticationException {
        IPasswordEncryptor passwordEncryptor = Injector.instance().getPasswordEncryptor();

        if (passwordEncryptor.passwordMatch(password, user.getPassword())) {

            List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();

            if (user.getBannerId().toUpperCase().equals(Admin_banner_id)) {
                rights.add(new SimpleGrantedAuthority("ADMIN"));

            } else {
                rights.add(new SimpleGrantedAuthority("USER"));

            }
            UsernamePasswordAuthenticationToken token;
            token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                    authentication.getCredentials(), rights);
            return token;
        } else {
            throw new BadCredentialsException("1000");
        }
    }
    
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String emailId = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        IUserRepository userRepository = Injector.instance().getUserRepository();

        IUser user = userAbstractFactory.createUserInstance();
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
