package com.example.softtectfinal.app.use.dto;

import lombok.Data;

@Data
public class UseUserUpdateRequestDto {
    private Long id;
    private String name;
    private String surname;
    private Long identityNo;
    private String password;
}
