package com.waruguru.msheadersservice.service;

import com.waruguru.msheadersservice.entity.StudentModel;
import com.waruguru.msheadersservice.entity.request.StudentDto;
import com.waruguru.msheadersservice.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    private StudentModel addNewStudent(StudentDto studentDto){
       StudentModel newStudentModel = new StudentModel();
       newStudentModel.setAge(studentDto.getAge());
       newStudentModel.setCreatedAt(LocalDateTime.now());
       newStudentModel.setCourseName(studentDto.getCourseName());
       newStudentModel.setFullName(studentDto.getFullName());
       newStudentModel.setPhoneNumber(studentDto.getPhoneNumber());

       StudentModel addedToDBSTudent = studentRepository.save(newStudentModel);

       return addedToDBSTudent;

    }
}
