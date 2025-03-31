package com.dueldeckapi.dto.User;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String email;
    private String userName;
}
