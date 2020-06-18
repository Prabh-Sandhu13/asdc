package CSCI5308.GroupFormationTool.AccessControl;

import java.sql.Date;
import java.util.ArrayList;

public interface IQuestion {

    long getId();

    void setId(long id);

    IUser getInstructor();

    void setInstructor(IUser instructor);

    String getTitle();

    void setTitle(String title);

    String getText();

    void setText(String text);

    int getType();

    void setType(int type);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    ArrayList<IChoice> getChoices();

    void setChoices(ArrayList<IChoice> choices);

}
