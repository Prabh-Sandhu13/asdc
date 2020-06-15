package CSCI5308.GroupFormationTool.AccessControl;

public interface IPolicy {

	public int getId();

	public void setId(int id);

	public String getSetting();

	public void setSetting(String setting);

	public String getValue();

	public void setValue(String value);

	public int getEnabled();

	public void setEnabled(int enabled);

}
