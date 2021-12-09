package com.hancom.authserver.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class LoginUserDto {

    private String username;

    private String password;

}
