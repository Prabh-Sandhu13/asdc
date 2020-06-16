package CSCI5308.GroupFormationTool.Security;

import CSCI5308.GroupFormationTool.AccessControl.IPasswordEncryptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncryption implements IPasswordEncryptor {

    private BCryptPasswordEncoder encode;

    public BCryptEncryption() {
        this.encode = new BCryptPasswordEncoder();
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
