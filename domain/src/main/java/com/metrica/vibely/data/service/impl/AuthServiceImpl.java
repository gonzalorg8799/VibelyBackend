package com.metrica.vibely.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.repository.UserRepository;
import com.metrica.vibely.data.service.AuthService;
import com.metrica.vibely.data.util.PasswordHashing;

/**
 * 
 * @since 2023-11-14
 * @version 1.0
 */
@Service
public class AuthServiceImpl implements AuthService {

    // <<-FIELD->>
    private UserRepository userRepository;

    // <<-CONSTRUCTOR->>
    @Autowired
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // <<-METHODS->>
    @Override
    public String authenticate(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow();
        
        try {
            if (PasswordHashing.matches(password, user.getPassword())) {
                user.setLogins(user.getLogins() + 1);
                // Generar una apikey !!!!
                return null;
            }
        } catch (Exception e) {
            System.err.println("fallo en password hashing");
        }
        return null;
    }

}
