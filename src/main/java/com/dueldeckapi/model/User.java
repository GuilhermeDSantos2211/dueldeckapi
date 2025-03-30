package com.dueldeckapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private static int idAuto = 0;
    private Integer id;
    private String userName;
    private String email;
    private String password;
}
