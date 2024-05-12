package com.jose.spring.springbootcrud.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jose.spring.springbootcrud.entities.Role;
import com.jose.spring.springbootcrud.entities.User;
import com.jose.spring.springbootcrud.repositories.RoleRepository;
import com.jose.spring.springbootcrud.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    //La idea es que siempre un usuario siempre tenga el rol user y que pueda o no tener el de admin
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {

        //Siempre va a tener un rol de usuario
        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();

        optionalRoleUser.ifPresent( role -> {
            roles.add(role);
        });

        //Este no sabemos si lo tendra, por eso llamamos a metodo para que nos lo confirme
        if(user.isAdmin()){
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent( role -> {
                roles.add(role);
            });
        }    
        //Le asignamos la lista de roles
        user.setRoles(roles);
        //Encriptar el password
        String passwordEncoded = passwordEncoder.encode(user.getPassword());
        //Pasamos el password codificado
        user.setPassword(passwordEncoded);
        
        return userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}