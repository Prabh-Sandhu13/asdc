package CSCI5308.GroupFormationTool.Security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncryption implements IPasswordEncryptor {

    private BCryptPasswordEncoder encode;

    public BCryptEncryption() {
        ISecurityAbstractFactory securityAbstractFactory = new SecurityAbstractFactory();
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
