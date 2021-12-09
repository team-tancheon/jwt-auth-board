package com.hancom.authserver.service;

import com.hancom.authserver.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService{

    User save(User user);

    Optional<User> findUserById(String userId);

    UserDetails loadUserByUsername(String username);

}
