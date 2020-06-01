package CSCI5308.GroupFormationTool.Model;

import CSCI5308.GroupFormationTool.AccessControl.ICourse;

public class Course implements ICourse {

	private String id;

	private String name;

	private int credits;

	private String description;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getCredits() {
		return credits;
	}

	@Override
	public void setCredits(int credits) {
		this.credits = credits;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	public Course() {
		id = null;
		name = null;
		credits = -1;
		description = null;
	}

}
