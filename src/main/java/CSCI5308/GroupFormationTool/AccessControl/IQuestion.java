package CSCI5308.GroupFormationTool.AccessControl;

import java.util.Calendar;

public interface IQuestion {

	public int getId();

	public void setId(int id);

	public IUser getInstructor();

	public void setInstructor(IUser instructor);

	public String getTitle();

	public void setTitle(String title);

	public String getText();

	public void setText(String text);

	public int getType();

	public void setType(int type);

	public Calendar getCreatedDate();

	public void setCreatedDate(Calendar createdDate);

}
