package com.hancom.authserver.serviceimpl;

import com.hancom.authserver.domain.User;
import com.hancom.authserver.dto.CreateUserDto;
import com.hancom.authserver.exception.DuplicateEmailException;
import com.hancom.authserver.repository.UserRepository;
import com.hancom.authserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserServieImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(CreateUserDto userDto) {
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            throw new IllegalArgumentException("Password do not match");
        }

        userRepository.findByEmail(userDto.getEmail())
                .ifPresent(user -> {
                    throw new DuplicateEmailException("Email is duplicated. email: " + userDto.getEmail());
                });

        User user = User.builder()
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .name(userDto.getName())
                .createDate(System.currentTimeMillis())
                .updateDate(System.currentTimeMillis())
                .build();

        return userRepository.save(user);
    }

    @Override
    public User findUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User is not found. id: " + userId));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User is not found. email: " + email));
    }

    @Override
    public boolean checkPassword(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email + " not found"));

        return passwordEncoder.matches(user.getPassword(), password);
    }

    //TODO: 권한 추가, 갱신

    //TODO: 권한 제거
}
