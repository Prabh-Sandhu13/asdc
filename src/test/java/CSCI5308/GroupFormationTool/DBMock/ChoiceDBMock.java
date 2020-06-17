package CSCI5308.GroupFormationTool.DBMock;

import CSCI5308.GroupFormationTool.AccessControl.IChoice;

public class ChoiceDBMock {

    private String text;

    private int value;

    public ChoiceDBMock() {
        text = "Amateur";
        value = 1;
    }

    public IChoice loadChoice(IChoice choice) {
        choice.setText(text);
        choice.setValue(value);
        return choice;
    }

}
