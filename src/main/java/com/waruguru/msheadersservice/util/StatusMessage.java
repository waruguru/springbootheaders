package com.waruguru.msheadersservice.util;

public class StatusMessage {
    public static final String CUSTOMER_SERVICE_UNAVAILABLE = "Request cannot be completed at this time." +
            " try again later";
    public static final String DEVELOPER_SERVICE_UNAVAILABLE = "Service is unavailable";
    public static final String CUSTOMER_BAD_REQUEST = "Your request cannot be processed. Check and try again";
    public static final String MISSING_REQUIRED_HEADER = "Missing required header";
    public static final String INVALID_CONVERSATION_ID = "Invalid Conversation ID";
    public static final String INVALID_SOURCE_APP = "Invalid Source App";
    public static final String HEADER_IS_REQUIRED = " header is required";
    public static final Object USER_NOT_AUTHORIZED ="User not authorized" ;
    public static final String X_IDENTIFIER = "X-Identifier";
    public static final String INVALID_IDENTIFIER = "Invalid Identifier";
}
