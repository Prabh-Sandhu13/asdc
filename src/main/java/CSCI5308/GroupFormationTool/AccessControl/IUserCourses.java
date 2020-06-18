package CSCI5308.GroupFormationTool.AccessControl;

public interface IUserCourses {

    String getCourseId();

    void setCourseId(String courseId);

    String getBannerId();

    void setBannerId(String bannerId);

    String getCourseName();

    void setCourseName(String courseName);

    String getCourseDescription();

    void setCourseDescription(String courseDescription);

    String getUserRole();

    void setUserRole(String userRole);
}
