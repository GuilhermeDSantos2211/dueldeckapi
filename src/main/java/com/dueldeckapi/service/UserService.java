package com.dueldeckapi.service;

import com.dueldeckapi.dto.User.UserLoginRequestDTO;
import com.dueldeckapi.dto.User.UserResponseDTO;
import com.dueldeckapi.dto.User.UserSignUpRequestDTO;
import com.dueldeckapi.mapper.UserMapper;
import com.dueldeckapi.model.UserEntity;
import com.dueldeckapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserResponseDTO signUpUser(UserSignUpRequestDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado!");
        }

        UserEntity user = new UserEntity();
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        UserEntity savedUser = userRepository.save(user);

        return userMapper.mapUserDTO(savedUser);
    }

    public UserResponseDTO userLogin(UserLoginRequestDTO dto) {
        UserEntity user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email não cadastrado: " + dto.getEmail()));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Senha incorreta!");
        }

        return userMapper.mapUserDTO(user);
    }
}
