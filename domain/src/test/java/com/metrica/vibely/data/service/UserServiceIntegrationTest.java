package com.metrica.vibely.data.service;

import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.model.mapper.UserMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @since 2023-12-05
 * @version 1.0
 */
@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    UserService userService;
    
    @Test
    void ssTest() {
        
        User user = new User();
        
        user.setUsername("jdoe");
        user.setPassword("12345");
        user.setEmail("jdoe@email.com");
        
        UserDTO userDTO = this.userService.create(UserMapper.toDTO(user));
        assertEquals("jdoe",           userDTO.getUsername());
        assertEquals("12345",          userDTO.getPassword());
        assertEquals("jdoe@email.com", userDTO.getEmail());
    }
    
}