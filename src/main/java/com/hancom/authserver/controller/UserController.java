package com.hancom.authserver.controller;

import com.hancom.authserver.api.BasicResponse;
import com.hancom.authserver.api.CommonResponse;
import com.hancom.authserver.domain.User;
import com.hancom.authserver.dto.CreateUserDto;
import com.hancom.authserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/v1/user")
@RestController
@Validated
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<? extends BasicResponse> createUser(@RequestBody @Valid CreateUserDto userDto) {
        User user = userService.createUser(userDto);
        return new ResponseEntity<>(new CommonResponse<>(user), HttpStatus.OK);
    }
}
