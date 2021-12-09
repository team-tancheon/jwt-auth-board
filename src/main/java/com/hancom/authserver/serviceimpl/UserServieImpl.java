package com.hancom.authserver.serviceimpl;

import com.hancom.authserver.domain.User;
import com.hancom.authserver.repository.UserRepository;
import com.hancom.authserver.service.UserService;
import com.hancom.authserver.utils.KeyProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserServieImpl implements UserService {

    private final UserRepository userRepository;

    public User save(User user){
        if(StringUtils.isEmpty(user.getUserId())){
            user.setUserId(KeyProvider.createKey());
            user.setCreateDate(System.currentTimeMillis());
        }
        user.setUpdateDate(System.currentTimeMillis());
        return userRepository.save(user);
    }

    // primary key 값으로 조회
    public Optional<User> findUserById(String userId){
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
