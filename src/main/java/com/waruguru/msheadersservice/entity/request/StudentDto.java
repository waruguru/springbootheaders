package com.waruguru.msheadersservice.entity.request;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@Builder
public class StudentDto {
    @NotEmpty(message = "Msisdn is required")
    @Pattern(regexp = "^(254|0)?([71])\\d{8}$", message = "Invalid Msisdn")
    @Size(min = 10, max = 12, message = "Invalid Msisdn")
    private String phoneNumber;
    @NotNull
    @Range(min = 0, max = 9)
    private long studentId;
    @NotNull
    @Range(min = 0, max = 9)
    private long age;
    @NotEmpty(message = "fullName Name is required!")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z-. ]+$", message = "Invalid fullName")
    @Size(min = 2, max = 100, message = "Invalid fullName Name")
    private String fullName;
    @NotEmpty(message = "courseName  is required!")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z-. ]+$", message = "Invalid courseName")
    @Size(min = 2, max = 100, message = "Invalid courseName")
    private String courseName;
    @NotEmpty
    private String createdAt;
    @NotEmpty
    private String updatedAt;

}
