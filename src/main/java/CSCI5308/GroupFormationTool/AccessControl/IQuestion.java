package CSCI5308.GroupFormationTool.AccessControl;

import java.sql.Date;
import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Model.Choice;

public interface IQuestion {

	public long getId();

	public void setId(long id);

	public IUser getInstructor();

	public void setInstructor(IUser instructor);

	public String getTitle();

	public void setTitle(String title);

	public String getText();

	public void setText(String text);

	public int getType();

	public void setType(int type);

	public Date getCreatedDate();

	public void setCreatedDate(Date createdDate);

	public ArrayList<IChoice> getChoices();

	public void setChoices(ArrayList<IChoice> choices);

}
