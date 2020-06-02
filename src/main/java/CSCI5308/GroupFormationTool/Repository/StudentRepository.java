package CSCI5308.GroupFormationTool.Repository;

import java.sql.SQLException;
import java.util.List;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Model.StudentCSV;
import CSCI5308.GroupFormationTool.AccessControl.*;

public class StudentRepository implements IStudentRepository {

private IPasswordEncryptor encryptor;
	
	@Override
	public boolean createStudent(List<StudentCSV> student)
	{
		encryptor = Injector.instance().getPasswordEncryptor();
		
		for(IStudentCSV stu : student)
		{
			try {
				StoredProcedure storedProcedure = new StoredProcedure("sp_create_user(?,?,?,?,?)");
				storedProcedure.setInputStringParameter(1, stu.getBannerId());
				storedProcedure.setInputStringParameter(2, stu.getFirstName());
				storedProcedure.setInputStringParameter(3, stu.getLastName());
				storedProcedure.setInputStringParameter(4, stu.getEmail());
				storedProcedure.setInputStringParameter(5, encryptor.encoder(stu.getPassword()));
				storedProcedure.execute();				
				
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				System.out.println(ex);
				ex.printStackTrace();
			}
		}
		
		return true;
	}
}
