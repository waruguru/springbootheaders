package com.waruguru.msheadersservice.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeaderResponse {
    private String requestRefId;
    private String responseCode;
    private String responseMessage;
    private String customerMessage=null;
    private String timestamp;
}
