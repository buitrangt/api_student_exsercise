package aibles.api_6.controller;

import aibles.api_6.service.StudentService;
import aibles.api_6.dto.StudentRequest;
import aibles.api_6.dto.StudentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@Slf4j
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponse create(@RequestBody StudentRequest request) {
        log.info(" === Start api create new student === ");
        log.info(" === Request Body : {} === ", request);
        StudentResponse response = service.create(request);
        log.info(" === Finish api create new student, Student Id : {} === ", response.getId());
        return response;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponse> getAllStudents() {
        log.info(" === Start api get all students === ");
        List<StudentResponse> responses = service.getAllStudents();
        log.info(" === Finish api get all students === ");
        return responses;
    }



    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentResponse updateStudent(@PathVariable String id, @RequestBody StudentRequest request) {
        log.info(" === Start api update student with id : {} === ", id);
        StudentResponse response = service.updateStudent(id, request);
        log.info(" === Finish api update student with id : {}, Updated student : {} === ", id, response);
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable String id) {
        log.info(" === Start api delete student with id : {} === ", id);
        service.deleteStudent(id);
        log.info(" === Finish api delete student with id : {} === ", id);
    }
}
