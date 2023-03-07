package com.waruguru.msheadersservice.service;

import com.waruguru.msheadersservice.entity.StudentModel;
import com.waruguru.msheadersservice.entity.request.StudentDto;
import com.waruguru.msheadersservice.entity.response.LogDto;
import com.waruguru.msheadersservice.exception.CustomException;
import com.waruguru.msheadersservice.repository.StudentRepository;
import com.waruguru.msheadersservice.util.StatusCode;
import com.waruguru.msheadersservice.util.StringConstants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentModel addNewStudent(LogDto logDto, StudentDto studentDto,String sourceApp){
       StudentModel newStudentModel = studentRepository.findByPhoneNumber(studentDto.getPhoneNumber()).orElseThrow(
               ()-> new CustomException(StatusCode.BAD_REQUEST,
                       logDto.getRequestId(), StringConstants.EXISTING_STUDENT,
                       StringConstants.EXISTING_STUDENT));


        newStudentModel.setAge(studentDto.getAge());
       newStudentModel.setCreatedAt(LocalDateTime.now());
       newStudentModel.setCourseName(studentDto.getCourseName());
       newStudentModel.setFullName(studentDto.getFullName());
       newStudentModel.setPhoneNumber(studentDto.getPhoneNumber());

       StudentModel addedToDBSTudent = studentRepository.save(newStudentModel);

       return addedToDBSTudent;

    }
    //get student by phoneNumber
    public Optional<StudentModel> fetchStudentByPhoneNumber(LogDto logDto, String phoneNumber, String sourceApp){
        Optional<StudentModel> optionalStudentModel = studentRepository.findByPhoneNumber(phoneNumber);

        if (optionalStudentModel.isEmpty()){
            throw new CustomException(StatusCode.NOT_FOUND,
                    logDto.getRequestId(),StringConstants.STUDENT_WITH_PHONE_NUMBER_NOT_FOUND,
                    StringConstants.STUDENT_WITH_PHONE_NUMBER_NOT_FOUND);
        }
        return optionalStudentModel;
    }

    //update existing student

    public StudentModel updateStudentDetail(LogDto logDto,StudentDto studentDto) {
        Optional<StudentModel> optionalStudentModel = studentRepository.findByPhoneNumber(studentDto.getPhoneNumber());

        if (optionalStudentModel.isEmpty()) {
            throw new CustomException(StatusCode.NOT_FOUND,
                    logDto.getRequestId(), StringConstants.STUDENT_WITH_PHONE_NUMBER_NOT_FOUND,
                    StringConstants.STUDENT_WITH_PHONE_NUMBER_NOT_FOUND);
        }
        StudentModel updatedStudent = optionalStudentModel.get();
        updatedStudent.setCourseName(studentDto.getCourseName());
        updatedStudent.setUpdatedAt(LocalDateTime.now());

        studentRepository.save(updatedStudent);

        return updatedStudent;
    }

    //delete records for a student
    public StudentModel deleteStudentsRecords(LogDto logDto, String phoneNumber){
        Optional<StudentModel> optionalStudentModel = studentRepository.findByPhoneNumber(phoneNumber);

        if (!optionalStudentModel.isPresent()){
            throw new CustomException(StatusCode.NOT_FOUND,
                    logDto.getRequestId(),StringConstants.STUDENT_WITH_PHONE_NUMBER_NOT_FOUND,
                    StringConstants.STUDENT_WITH_PHONE_NUMBER_NOT_FOUND);

        }
        studentRepository.deleteByPhoneNumber(phoneNumber);

        return null;
    }

}
