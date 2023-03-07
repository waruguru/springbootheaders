package com.waruguru.msheadersservice.controller;

import com.waruguru.msheadersservice.entity.request.StudentDto;
import com.waruguru.msheadersservice.entity.response.LogDto;
import com.waruguru.msheadersservice.service.StudentService;
import com.waruguru.msheadersservice.service.UtilService;
import com.waruguru.msheadersservice.util.StringConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;

import static com.waruguru.msheadersservice.service.UtilService.buildResponse;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final UtilService utilService;
    private final StudentService studentService;

    @RequestMapping(value = "/addToDB",method = RequestMethod.POST)
    public ResponseEntity<?> addNewStudentToDB(@RequestHeader HttpHeaders headers,
                                               @RequestBody @Valid StudentDto studentDto,
                                               HttpServlet requet){
        LogDto logDto = utilService.validateHeaders(headers);
        return buildResponse(logDto,studentService.addNewStudent(logDto,
                studentDto,headers.getFirst(StringConstants.X_SOURCE_SYSTEM)),StringConstants.STUDENT_SUCCESSFULLY_ADDED_TO_DB);
    }

    @GetMapping("/filterByPhoneNumber")
    public ResponseEntity<?> getAStudentByPhoneNumber(
            @RequestHeader HttpHeaders headers,String phoneNumber,String sourceApp){
                LogDto logDto = utilService.validateHeaders(headers);
                return buildResponse(logDto,studentService.fetchStudentByPhoneNumber(logDto,phoneNumber,sourceApp),StringConstants.STUDENT_RECORD_FETCHED_SUCCESSFULLY);
    }


    @PutMapping("/updateStudentRecords")
    public ResponseEntity<?> updateStudentsRecord(@RequestHeader HttpHeaders headers,
                                                  @RequestBody StudentDto studentDto){
                LogDto logDto = utilService.validateHeaders(headers);
                return buildResponse(logDto,studentService.updateStudentDetail(logDto,studentDto),StringConstants.STUDENT_REcORDS_UPDATED_SUCCESSFULLY);
    }

    @RequestMapping(value = "/deleteStudentRecords",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStudentRecordByPhoneNumber(
            @RequestHeader HttpHeaders headers,String phoneNumber){
                LogDto logDto = utilService.validateHeaders(headers);
                return buildResponse(logDto,studentService.deleteStudentsRecords(logDto,phoneNumber),StringConstants.STUDENTS_DETAILS_DELETED_SUCCESSFULLY);
    }
}
