package CSCI5308.GroupFormationTool.AccessControl;

public interface IPolicy {

    int getId();

    void setId(int id);

    String getSetting();

    void setSetting(String setting);

    String getValue();

    void setValue(String value);

    int getEnabled();

    void setEnabled(int enabled);

}
