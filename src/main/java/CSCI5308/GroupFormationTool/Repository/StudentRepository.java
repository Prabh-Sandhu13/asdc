package CSCI5308.GroupFormationTool.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.text.RandomStringGenerator;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Database.ConnectionManager;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Model.StudentCSV;
import CSCI5308.GroupFormationTool.AccessControl.*;

public class StudentRepository implements IStudentRepository {

private IPasswordEncryptor encryptor;
List<String> emails = new ArrayList<>();
List<StudentCSV> newStudents = new ArrayList<StudentCSV>();
List<StudentCSV> oldStudents = new ArrayList<StudentCSV>();
List<StudentCSV> badData = new ArrayList<StudentCSV>();
	
	@Override
	public Map<Integer,List<StudentCSV>> createStudent(List<StudentCSV> student, String courseId)
	{
		encryptor = Injector.instance().getPasswordEncryptor();
		RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 45).build();
		
		Map<Integer,List<StudentCSV>> studentLists = new HashMap<Integer,List<StudentCSV>>();
		
		for(StudentCSV stu : student)
		{
			if(stu.getBannerId() == null || stu.getFirstName() == null || stu.getLastName() == null || stu.getEmail() == null)
			{
				badData.add(stu);
			}
			else
			{
				try {	
					Connection con = ConnectionManager.instance().getDBConnection();
					CallableStatement cstmt = con.prepareCall("{call sp_createStudentFromCSV(?,?,?,?,?,?,?)}");
					cstmt.setString(1, stu.getBannerId());
					cstmt.setString(2, stu.getFirstName());
					cstmt.setString(3, stu.getLastName());
					cstmt.setString(4, stu.getEmail());
					String password = pwdGenerator.generate(10);
					stu.setPassword(password);
					cstmt.setString(5, encryptor.encoder(password));
					cstmt.setString(6, courseId);
					cstmt.registerOutParameter(7, Types.BOOLEAN);
					cstmt.execute();
					Boolean studentStatus = cstmt.getBoolean(7);
					System.out.println(studentStatus);
					
					if(studentStatus)
					{
						newStudents.add(stu);
					}
					else
					{
						try
						{
						oldStudents.add(stu);
						}
						catch(Exception ex)
						{
							System.out.println(ex);
							ex.getStackTrace();
						}
					}
					
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					System.out.println(ex);
					ex.printStackTrace();
				}
			}				
		}
		
		studentLists.put(0,newStudents);		
		studentLists.put(1, oldStudents);
		studentLists.put(2, badData);
		
		return studentLists;
	}
}
