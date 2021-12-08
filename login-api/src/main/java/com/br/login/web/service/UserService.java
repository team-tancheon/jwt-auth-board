package com.br.login.web.service;

import com.br.login.web.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService{

    User save(User user);

    Optional<User> findUser(String userId);

    UserDetails loadUserByUsername(String username);

}
