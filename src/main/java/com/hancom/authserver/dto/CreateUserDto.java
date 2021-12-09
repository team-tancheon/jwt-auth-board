package com.hancom.authserver.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class CreateUserDto {

    @NotEmpty
    private String email;   // TODO - @Valid Annotation 이용한 유효성 검사 처리 필요

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;
}
