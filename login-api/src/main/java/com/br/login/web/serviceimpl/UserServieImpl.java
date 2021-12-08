package com.br.login.web.serviceimpl;

import com.br.login.web.domain.User;
import com.br.login.web.repository.UserRepository;
import com.br.login.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserServieImpl implements UserService {

    private final UserRepository userRepository;

    public User save(User user){
        if(StringUtils.isEmpty(user.getUserId())){
            user.setCreateDate(System.currentTimeMillis());
        }
        user.setUpdateDate(System.currentTimeMillis());
        return userRepository.save(user);
    }

    // primary key 값으로 조회
    public Optional<User> findUser(String userId){
        return userRepository.findById(userId);
    }

    // email로 조회
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException(username+" not found"));
    }

    //TODO: email 변경

    //TODO: 권한 추가, 갱신

    //TODO: 권한 제거
}
