package CSCI5308.GroupFormationTool.Security;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.FactoryProducer;
import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.User.IUser;
import CSCI5308.GroupFormationTool.User.IUserRepository;
import CSCI5308.GroupFormationTool.User.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class CustomAuthenticationManager implements AuthenticationManager {

    private static final String Admin_banner_id = "B00000000";

    private ISecurityAbstractFactory securityAbstractFactory = FactoryProducer.getFactory().
            createSecurityAbstractFactory();

    private Authentication checkUser(String password, IUser user, Authentication authentication)
            throws AuthenticationException {
        IPasswordEncryptor passwordEncryptor = Injector.instance().getPasswordEncryptor();

        if (passwordEncryptor.passwordMatch(password, user.getPassword())) {

            List<GrantedAuthority> rights = securityAbstractFactory.createGrantedAuthorityListInstance();

            if (user.getBannerId().toUpperCase().equals(Admin_banner_id)) {
                rights.add(securityAbstractFactory.createSimpleGrantedAuthority(DomainConstants.AdminRole));
            } else {
                rights.add(securityAbstractFactory.createSimpleGrantedAuthority(DomainConstants.UserRole));
            }
            UsernamePasswordAuthenticationToken token;
            token = securityAbstractFactory.createUsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                    authentication.getCredentials(), rights);
            return token;
        } else {
            throw securityAbstractFactory.createBadCredentialsExceptionInstance("1000");
        }
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String emailId = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        IUserRepository userRepository = Injector.instance().getUserRepository();

        IUser user = new User();
        user.setEmailId(emailId);
        try {
            user = userRepository.getUserByEmailId(user);
        } catch (Exception e) {
            throw securityAbstractFactory.createAuthenticationServiceExceptionInstance("1000");
        }
        if (user != null) {
            return checkUser(password, user, authentication);
        } else {
            throw securityAbstractFactory.createBadCredentialsExceptionInstance("1001");
        }
    }
}
