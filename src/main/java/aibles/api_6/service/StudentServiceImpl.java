package aibles.api_6.service;

import aibles.api_6.entity.StudentEntity;
import aibles.api_6.exception.NotFoundException;
import aibles.api_6.repository.StudentRepository;

import aibles.api_6.dto.StudentRequest;
import aibles.api_6.dto.StudentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static aibles.api_6.service.StudentMapping.convertDtoToEntity;
import static aibles.api_6.service.StudentMapping.convertEntityToStudentResponse;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponse create(StudentRequest request) {
        log.info(" === Start api create new student === ");
        log.info(" === Request Body : {} === ", request);
        try {
            StudentEntity entity = convertDtoToEntity(request);
            entity = studentRepository.save(entity);
            StudentResponse response = convertEntityToStudentResponse(entity);
            log.info(" === Finish api create new student, Student id : {} === ", response.getId());
            return response;
        } catch (Exception ex) {
            log.error("Error occurred while creating student: {}", ex.getMessage());
            throw ex;
        }
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        log.info(" === Start api get all students === ");
        try {
            List<StudentEntity> entities = studentRepository.findAll();
            List<StudentResponse> responses = entities.stream()
                    .map(StudentMapping::convertEntityToStudentResponse)
                    .collect(Collectors.toList());
            log.info(" === Finish api get all students === ");
            return responses;
        } catch (Exception ex) {
            log.error("Error occurred while getting all students: {}", ex.getMessage());
            throw ex;
        }
    }

    @Override
    public StudentResponse getStudentById(String id) {
        log.info(" === Start api get student by id : {} === ", id);
        try {
            Optional<StudentEntity> optionalEntity = studentRepository.findById(id);
            if (optionalEntity.isPresent()) {
                StudentResponse response = convertEntityToStudentResponse(optionalEntity.get());
                log.info(" === Finish api get student by id : {}, Student : {} === ", id, response);
                return response;
            } else {
                log.info(" === Finish api get student by id : {}, Student not found === ", id);
                throw new NotFoundException("Student not found with id: " + id);
            }
        } catch (Exception ex) {
            log.error("Error occurred while getting student by id: {}", ex.getMessage());
            throw ex;
        }
    }

    @Override
    public StudentResponse updateStudent(String id, StudentRequest request) {
        log.info(" === Start api update student with id : {} === ", id);
        try {
            Optional<StudentEntity> optionalEntity = studentRepository.findById(id);
            if (optionalEntity.isPresent()) {
                StudentEntity entity = optionalEntity.get();
                entity.setName(request.getName());
                entity.setPassword(request.getPassword());
                entity.setLastname(request.getLastname());
                entity = studentRepository.save(entity);
                StudentResponse response = convertEntityToStudentResponse(entity);
                log.info(" === Finish api update student with id : {}, Updated student : {} === ", id, response);
                return response;
            } else {
                log.info(" === Finish api update student with id : {}, Student not found === ", id);
                throw new NotFoundException("Student not found with id: " + id);
            }
        } catch (Exception ex) {
            log.error("Error occurred while updating student with id: {}", ex.getMessage());
            throw ex;
        }
    }

    @Override
    public void deleteStudent(String id) {
        log.info(" === Start api delete student with id : {} === ", id);
        try {
            studentRepository.deleteById(id);
            log.info(" === Finish api delete student with id : {} === ", id);
        } catch (Exception ex) {
            log.error("Error occurred while deleting student with id: {}", ex.getMessage());
            throw ex;
        }
    }
}
