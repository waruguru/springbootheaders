package com.waruguru.msheadersservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private String statusCode;
    private String requestId;
    private String developerMessage;
    private String customerMessage;
}
