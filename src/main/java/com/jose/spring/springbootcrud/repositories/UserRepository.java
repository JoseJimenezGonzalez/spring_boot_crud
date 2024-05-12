package com.jose.spring.springbootcrud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jose.spring.springbootcrud.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{

    boolean existsByUsername(String username);
}
