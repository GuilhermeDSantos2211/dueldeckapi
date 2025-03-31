package com.dueldeckapi.controller;

import com.dueldeckapi.dto.User.UserLoginRequestDTO;
import com.dueldeckapi.dto.User.UserResponseDTO;
import com.dueldeckapi.dto.User.UserSignUpRequestDTO;
import com.dueldeckapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<UserResponseDTO> signUp(@Valid @RequestBody UserSignUpRequestDTO dto) {
        UserResponseDTO newUser = userService.signUpUser(dto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@Valid @RequestBody UserLoginRequestDTO dto) {
        UserResponseDTO user = userService.userLogin(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
