package CSCI5308.GroupFormationTool.Service;

import CSCI5308.GroupFormationTool.AccessControl.IMailService;
import CSCI5308.GroupFormationTool.AccessControl.IStudentRepository;
import CSCI5308.GroupFormationTool.AccessControl.IStudentService;
import CSCI5308.GroupFormationTool.DomainConstants;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Model.StudentCSV;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentService implements IStudentService {

    private IStudentRepository studentRepository;
    private IMailService mailService;

    @Override
    public Map<Integer, List<StudentCSV>> createStudent(MultipartFile file, String courseId) {
        studentRepository = Injector.instance().getStudentRepository();
        mailService = Injector.instance().getMailService();
        
        List<StudentCSV> badData = new ArrayList<StudentCSV>();
        List<StudentCSV> properData = new ArrayList<StudentCSV>();
        Map<Integer, List<StudentCSV>> studentLists = null;

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            CsvToBean<StudentCSV> csvToBean = new CsvToBeanBuilder<StudentCSV>(reader).withType(StudentCSV.class)
                    .withIgnoreLeadingWhiteSpace(true).build();

            List<StudentCSV> students = csvToBean.parse();
            for (StudentCSV studentCSV : students) {
                if (checkForBadData(studentCSV)) {
                    badData.add(studentCSV);
                }
                else {
                	properData.add(studentCSV);
                }
            }
            studentLists = studentRepository.createStudent(properData, courseId);
            studentLists.put(DomainConstants.badData, badData);

            mailService.sendBatchMail(studentLists.get(DomainConstants.newStudents), courseId);

        } catch (Exception ex) {
            return null;
        }
        return studentLists;
    }
    
    private boolean checkForBadData(StudentCSV studentCSV) {
        if (studentCSV.getBannerId() == null || studentCSV.getFirstName() == null
                || studentCSV.getLastName() == null || studentCSV.getEmail() == null
                || studentCSV.getBannerId().equals("") || studentCSV.getFirstName().equals("")
                || studentCSV.getLastName().equals("") || studentCSV.getEmail().equals("")) {
            return true;
        } else {
            return false;
        }
    }
}
