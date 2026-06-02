package me.scpark.springdeveloper.service;


import lombok.RequiredArgsConstructor;
import me.scpark.springdeveloper.dto.AddUserRequest;
import me.scpark.springdeveloper.repository.UserRepository;
import me.scpark.springdeveloper.dao.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public Long save(AddUserRequest dto){
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }
}
