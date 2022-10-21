package com.miusicmaker.musmk.services;

;
import com.miusicmaker.musmk.dto.UserSignIn;
import com.miusicmaker.musmk.dto.UserSignUp;

public interface UserService {

    // exit code here
    int signIn(UserSignIn signUp);
    // exit code here
    int signUp(UserSignUp signIn);

}
