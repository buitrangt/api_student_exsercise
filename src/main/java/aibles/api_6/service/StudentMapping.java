package aibles.api_6.service;

import aibles.api_6.entity.StudentEntity;
import aibles.api_6.dto.StudentRequest;
import aibles.api_6.dto.StudentResponse;

public class StudentMapping {
    public static StudentEntity convertDtoToEntity(StudentRequest dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setLastname(dto.getLastname());
        return entity;
    }

    public static StudentResponse convertEntityToStudentResponse(StudentEntity entity) {
        StudentResponse dto = new StudentResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPassword(entity.getPassword());
        dto.setLastname(entity.getLastname());
        return dto;
    }
}
