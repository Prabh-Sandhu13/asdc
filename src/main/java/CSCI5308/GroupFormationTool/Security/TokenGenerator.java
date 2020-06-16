package CSCI5308.GroupFormationTool.Security;

import CSCI5308.GroupFormationTool.AccessControl.ITokenGenerator;

import java.util.UUID;

public class TokenGenerator implements ITokenGenerator {

    @Override
    public String generator() {
        return UUID.randomUUID().toString();
    }

}
