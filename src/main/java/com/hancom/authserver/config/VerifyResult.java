package com.hancom.authserver.config;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class VerifyResult {

    private String userId;
    private boolean result;

}
