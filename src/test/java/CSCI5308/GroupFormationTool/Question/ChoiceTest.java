package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.Question.IChoice;
import CSCI5308.GroupFormationTool.Question.ChoiceDBMock;
import CSCI5308.GroupFormationTool.Question.Choice;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ChoiceTest {

    public IChoice createDefaultChoice() {
        ChoiceDBMock choiceDBMock = new ChoiceDBMock();
        IChoice choice = loadChoice(choiceDBMock);
        return choice;
    }

    public IChoice loadChoice(ChoiceDBMock choiceDBMock) {
        IChoice choice = new Choice();
        choice = choiceDBMock.loadChoice(choice);
        return choice;
    }

    @Test
    public void getText() {
        IChoice choice = createDefaultChoice();
        assertEquals("Amateur", choice.getText());
    }

    @Test
    public void setText() {
        IChoice choice = new Choice();
        choice.setText("Beginner");
        assertEquals("Beginner", choice.getText());
    }

    @Test
    public void getValue() {
        IChoice choice = createDefaultChoice();
        assertEquals(1, choice.getValue());
    }

    @Test
    public void setValue() {
        IChoice choice = new Choice();
        choice.setValue(2);
        assertEquals(2, choice.getValue());
    }
}
