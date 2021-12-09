package com.hancom.authserver.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ErrorResponse extends BasicResponse {
    private final String errorCode;
    private final String errorMessage;

    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = "404";
    }

    public ErrorResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
