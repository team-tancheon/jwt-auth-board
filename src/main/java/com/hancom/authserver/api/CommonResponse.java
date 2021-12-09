package com.hancom.authserver.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class CommonResponse<T> extends BasicResponse {
    private final int count;
    private final T data;

    public CommonResponse(T data) {
        this.data = data;

        if(data instanceof List) {
            this.count = ((List<?>) data).size();
        }else {
            this.count = 1;
        }
    }
}
