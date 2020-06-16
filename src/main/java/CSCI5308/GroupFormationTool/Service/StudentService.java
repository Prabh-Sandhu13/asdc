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
import java.util.List;
import java.util.Map;

public class StudentService implements IStudentService {

    private IStudentRepository studentRepository;
    private IMailService mailService;

    @Override
    public Map<Integer, List<StudentCSV>> createStudent(MultipartFile file, String courseId) {
        studentRepository = Injector.instance().getStudentRepository();
        mailService = Injector.instance().getMailService();

        Map<Integer, List<StudentCSV>> studentLists = null;

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            CsvToBean<StudentCSV> csvToBean = new CsvToBeanBuilder<StudentCSV>(reader).withType(StudentCSV.class)
                    .withIgnoreLeadingWhiteSpace(true).build();

            List<StudentCSV> students = (List<StudentCSV>) csvToBean.parse();
            studentLists = studentRepository.createStudent(students, courseId);

            mailService.sendBatchMail(studentLists.get(DomainConstants.newStudents), courseId);

        } catch (Exception ex) {

            return null;
        }

        return studentLists;

    }

}
