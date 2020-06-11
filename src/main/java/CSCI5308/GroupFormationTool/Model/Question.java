package CSCI5308.GroupFormationTool.Model;

import java.sql.Date;
import java.util.Calendar;

import CSCI5308.GroupFormationTool.AccessControl.IQuestion;
import CSCI5308.GroupFormationTool.AccessControl.IUser;

public class Question implements IQuestion {

	private int id;

	private IUser instructor;

	private String title;

	private String text;

	private int type;

	private Calendar createdDate;

	public Question() {
		this.id = -1;
		this.instructor = null;
		this.title = null;
		this.text = null;
		this.type = -1;
		createdDate = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public IUser getInstructor() {
		return instructor;
	}

	public void setInstructor(IUser instructor) {
		this.instructor = instructor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Calendar getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

}
