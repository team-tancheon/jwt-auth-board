package com.br.login.web.config;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class VerifyResult {

    private String userId;
    private boolean result;

}
