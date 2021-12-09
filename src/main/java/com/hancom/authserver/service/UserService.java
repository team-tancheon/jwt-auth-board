package com.hancom.authserver.service;

import com.hancom.authserver.domain.User;
import com.hancom.authserver.dto.CreateUserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{

    User createUser(CreateUserDto createUserDto);

    User findUserById(String userId);

    UserDetails loadUserByUsername(String username);

    boolean checkPassword(String email, String password);

}
