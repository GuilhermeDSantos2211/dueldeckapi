package com.dueldeckapi.dto.User;

import lombok.Data;

@Data
public class UserSignUpRequestDTO {
    private String userName;
    private String email;
    private String password;
}
