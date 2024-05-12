package com.jose.spring.springbootcrud.services;

import java.util.List;

import com.jose.spring.springbootcrud.entities.User;

public interface UserService {

    List<User> findAll();
    User save(User user);
    boolean existsByUsername(String username);
}
