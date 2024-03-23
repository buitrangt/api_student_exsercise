package aibles.api_6.Service;

import aibles.api_6.dto.StudentRequest;
import aibles.api_6.dto.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse create(StudentRequest request);

    List<StudentResponse> getAllStudents();

    StudentResponse getStudentById(String id);

    StudentResponse updateStudent(String id, StudentRequest request);

    void deleteStudent(String id);
}
