package com.miusicmaker.musmk.mappers;

import com.miusicmaker.musmk.dto.UserSignIn;
import com.miusicmaker.musmk.model.User;

import java.util.function.Function;

public class Mappers {

    /*public static Function<UserSignIn, User> fromSignInToUser = sign
             -> User.builder()
            .email(sign.getEmail())
            .nickname(sign.getNickname())
            .password(sign.getPassword())
            .build();*/

    public static Function<UserSignIn, User> fromSignUpToUser = signUp
            -> User.builder()
            .password(signUp.getPassword())
            .nickname(signUp.getLoin())
            .email(signUp.getEmail())
            .build();

}
