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
                studentDto,headers.getFirst(StringConstants.X_SOURCE_SYSTEM)),StringConstants.WORK_ORDER_CREATED_SUCCESSFUL);
    }
}
