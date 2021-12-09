package com.hancom.authserver.controller;

import com.hancom.authserver.api.BasicResponse;
import com.hancom.authserver.api.CommonResponse;
import com.hancom.authserver.api.ErrorResponse;
import com.hancom.authserver.domain.User;
import com.hancom.authserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/v1/user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<? extends BasicResponse> findUserById(@PathVariable String userId){
        Optional<User> user = userService.findUserById(userId);

        if(!user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("사용자를 찾을 수 없습니다."));
        }
        return ResponseEntity.ok().body(new CommonResponse<>(user.get()));
    }

}
