package com.metrica.vibely.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.service.UserService;
import com.metrica.vibely.model.request.CreateUserRequest;

import jakarta.validation.Valid;

/**
 * @since 2023-11-14
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    // <<-FIELDS->>
    private UserService userService;

    // <<-CONSTRUCTOR->>
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//     <<-METHODS->>
    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable UUID id) {
        return this.userService.getById(id);
    }
    
    @GetMapping("/{username}")
    public UserDTO getByUsername(@PathVariable String username) {
        return this.userService.getByUsername(username);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> create(
            @RequestBody
            @Valid
            CreateUserRequest createUser,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().build();
        UserDTO userDto = CreateUserRequest.toUserDTO(createUser);
        UserDTO user = this.userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserDTO> modifyByUsername(@RequestBody @Valid CreateUserRequest createUser, BindingResult bindingResult) {
        UserDTO userDto = CreateUserRequest.toUserDTO(createUser);
        UserDTO user = this.userService.update(userDto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteByUsername(@PathVariable String username) {
        this.userService.deleteByUsername(username);
        return ResponseEntity.noContent().build();
    }

}
