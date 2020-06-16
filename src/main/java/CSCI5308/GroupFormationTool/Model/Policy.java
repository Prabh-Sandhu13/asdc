package CSCI5308.GroupFormationTool.Model;

import CSCI5308.GroupFormationTool.AccessControl.IPolicy;

public class Policy implements IPolicy {
	private int id;
	private String setting;
	private String value;
	private int enabled;

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getSetting() {
		return setting;
	}

	@Override
	public void setSetting(String setting) {
		this.setting = setting;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int getEnabled() {
		return enabled;
	}

	@Override
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

}
