package com.waruguru.msheadersservice.service;

import com.waruguru.msheadersservice.entity.response.ApiResponse;
import com.waruguru.msheadersservice.entity.response.HeaderResponse;
import com.waruguru.msheadersservice.entity.response.LogDto;
import com.waruguru.msheadersservice.exception.CustomException;
import com.waruguru.msheadersservice.util.StatusCode;
import com.waruguru.msheadersservice.util.StatusMessage;
import com.waruguru.msheadersservice.util.StringConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UtilService {
    public LogDto validateHeaders(HttpHeaders headers) {
        validateNotNullHeaders(headers);
        String requestId = headers.getFirst(StringConstants.X_CORRELATION_CONVERSATION_ID);
        validateConversationId(headers.getFirst(StringConstants.X_CORRELATION_CONVERSATION_ID));
        checkMsisdn(headers, StringConstants.X_IDENTIFIER);

        return LogDto
                .builder()
                .sourceApp(headers.getFirst(StringConstants.X_SOURCE_APP))
                .identifier(headers.getFirst(StringConstants.X_IDENTIFIER))
                .requestId(headers.getFirst(StringConstants.X_CORRELATION_CONVERSATION_ID))
                .build();
    }

    private void validateNotNullHeaders(HttpHeaders headers) {
        String requestId = headers.getFirst(StringConstants.X_CORRELATION_CONVERSATION_ID);
        if (!headers.containsKey(StringConstants.X_CORRELATION_CONVERSATION_ID)) {
            throw new CustomException(StatusCode.BAD_REQUEST, requestId, StringConstants.X_CORRELATION_CONVERSATION_ID
                    + StatusMessage.HEADER_IS_REQUIRED
                    , StatusMessage.MISSING_REQUIRED_HEADER);
        }
        if (!headers.containsKey(StringConstants.X_SOURCE_APP)) {
            throw new CustomException(StatusCode.BAD_REQUEST, requestId, StringConstants.X_SOURCE_APP + StatusMessage
                    .HEADER_IS_REQUIRED
                    , StatusMessage.MISSING_REQUIRED_HEADER);
        }
        if (!headers.containsKey(StringConstants.X_IDENTIFIER)) {
            throw new CustomException(StatusCode.BAD_REQUEST, requestId, StringConstants.X_IDENTIFIER + StatusMessage
                    .HEADER_IS_REQUIRED
                    , StatusMessage.MISSING_REQUIRED_HEADER);
        }

    }

    private void validateConversationId(String conversationId) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9-]+$");
        Matcher matcher = pattern.matcher(conversationId);
        if (!matcher.matches()) {
            throw new CustomException(StatusCode.BAD_REQUEST, conversationId, StatusMessage.INVALID_CONVERSATION_ID
                    , StatusMessage.INVALID_CONVERSATION_ID);
        }
    }


    public static String maskMsisdn(String msisdn) {
        return msisdn.substring(0, 5).concat("****").concat(msisdn.substring(8));
    }
    //Check msisdn
    public void checkMsisdn(HttpHeaders headers, String header) {
        if(!headers.containsKey(header)){
            throw new CustomException(StatusCode.BAD_REQUEST, "", StringConstants.X_CORRELATION_CONVERSATION_ID
                    + StatusMessage.HEADER_IS_REQUIRED
                    , StatusMessage.MISSING_REQUIRED_HEADER);
        }

        String msisdn = headers.getFirst(header);
        String email = headers.getFirst(header);

        if((msisdn == null || !isNumber(msisdn) || !isValidMsisdn(msisdn)) &&(email == null || !isValidEmail(email))){
            throw new CustomException(StatusCode.BAD_REQUEST, maskMsisdn(msisdn), StatusMessage.INVALID_IDENTIFIER
                    ,StatusMessage.INVALID_IDENTIFIER);

        }
    }
    //is email valid
    public static boolean isValidEmail(String email) {
        String patternStr = "^(.+)@(\\S+)$";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static ResponseEntity<?> buildResponse(LogDto logDto, Object obj,String customerMessage) {
        HeaderResponse header = new HeaderResponse(
                logDto.getRequestId(),
                "200",
                customerMessage,
                customerMessage,
                new Date(System.currentTimeMillis()).toString()
        );
        return ResponseEntity.ok( new ApiResponse(header, obj));
    }

  //  Is phone number valid
    private boolean isValidMsisdn(String msisdn) {
        String patternStr = "^(254|0)?(7|1)\\d{8}$";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(msisdn);
        return matcher.matches();
    }
    //Check if msisdn is a number
    private boolean isNumber(String msisdn) {
        try {
            Long.parseLong(msisdn);
            return true;
        } catch (NumberFormatException ex) {
            return  false;
        }
    }





}
