package com.security.kys95.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;


@Data
public class UserInfoDto {
    private int id;
    private String email;
    private String role;
    private String provider;
    private Timestamp createDate;

    @Builder
    UserInfoDto(int id, String email, String role, String provider, Timestamp createDate){
        this.id = id;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.createDate = createDate;
    }
}

