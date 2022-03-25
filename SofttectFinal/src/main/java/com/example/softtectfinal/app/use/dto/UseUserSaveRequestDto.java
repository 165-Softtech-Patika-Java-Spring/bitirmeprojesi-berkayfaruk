package com.example.softtectfinal.app.use.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UseUserSaveRequestDto {
    private String name;
    private String surname;
    private Long identityNo;
    private String password;
}
