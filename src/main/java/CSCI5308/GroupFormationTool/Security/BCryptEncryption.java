package CSCI5308.GroupFormationTool.Security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import CSCI5308.GroupFormationTool.Common.Injector;

public class BCryptEncryption implements IPasswordEncryptor {

    private BCryptPasswordEncoder encode;

    private ISecurityAbstractFactory securityAbstractFactory = Injector.instance().getSecurityAbstractFactory();

    public BCryptEncryption() {
        this.encode = securityAbstractFactory.createBCryptPasswordEncoder();
    }

    @Override
    public String encoder(String password) {
        return encode.encode(password);
    }

    @Override
    public boolean passwordMatch(String password, String encryptedPassword) {
        return encode.matches(password, encryptedPassword);
    }
}
