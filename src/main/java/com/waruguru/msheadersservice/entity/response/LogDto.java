package com.waruguru.msheadersservice.entity.response;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class LogDto {
    private String requestId;
    private String sourceApp;
    private String identifier;

}
