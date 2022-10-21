package com.miusicmaker.musmk.services;

import com.miusicmaker.musmk.dto.UserSignIn;
import com.miusicmaker.musmk.dto.UserSignUp;
import com.miusicmaker.musmk.mappers.Mappers;
import com.miusicmaker.musmk.model.User;
import com.miusicmaker.musmk.repositories.UserRepositoryImpl;

import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {

    // нельзя брать другой репо так что финалка
    private final UserRepositoryImpl repo;

    public UserServiceImpl(UserRepositoryImpl repo) {
        this.repo = repo;
    }

    @Override
    public int signIn(UserSignIn signIn) {

        User newUser = Mappers.fromSignUpToUser.apply(signIn);

        repo.save(newUser);

        return 1;
    }

    @Override
    public int signUp(UserSignUp signUp) {

        List<User> users = repo.findAll();

        int exitCode;

        // было бы в кайф через BinarySearch, но пока пусть так
        for (int i = 0; i < users.size(); i++) {

            if (Objects.equals(signUp.getNickname(), users.get(i).getNickname())
                && Objects.equals(signUp.getPassword(), users.get(i).getPassword())
            ){

                System.out.println("USER FOUNDED!");

            }

        }

        return 1;
    }

}
