package com.hancom.authserver.api;

import lombok.Getter;

import java.util.List;

@Getter
public class CommonResponse<T> extends BasicResponse {
    private final int count;
    private final T data;

    public CommonResponse() {
        this(null);
    }

    public CommonResponse(T data) {
        this.data = data;

        if (data instanceof List) {
            this.count = ((List<?>) data).size();
        } else {
            this.count = 1;
        }
    }
}
