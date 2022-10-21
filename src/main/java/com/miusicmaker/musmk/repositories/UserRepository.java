package com.miusicmaker.musmk.repositories;

import com.miusicmaker.musmk.model.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findById(Long id);

    User findByLoginAndPassword(String email, String password);

    void save(User u);

    void update(User u);

    void delete(Long id);

}
