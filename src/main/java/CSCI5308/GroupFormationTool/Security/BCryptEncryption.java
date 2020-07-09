package CSCI5308.GroupFormationTool.Security;

import CSCI5308.GroupFormationTool.Common.FactoryProducer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncryption implements IPasswordEncryptor {

    private BCryptPasswordEncoder encode;

    private ISecurityAbstractFactory securityAbstractFactory = FactoryProducer.getFactory().
            createSecurityAbstractFactory();

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
