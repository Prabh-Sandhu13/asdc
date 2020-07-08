package CSCI5308.GroupFormationTool.Password;

import CSCI5308.GroupFormationTool.Password.ITokenGenerator;

import java.util.UUID;

public class TokenGenerator implements ITokenGenerator {

    @Override
    public String generator() {
        return UUID.randomUUID().toString();
    }

}
