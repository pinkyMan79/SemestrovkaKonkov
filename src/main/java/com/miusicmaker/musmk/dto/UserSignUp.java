package com.miusicmaker.musmk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserSignUp {

    private String email;
    private String password;
    private String nickname;


}
