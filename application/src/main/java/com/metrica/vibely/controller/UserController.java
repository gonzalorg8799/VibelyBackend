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

import com.metrica.vibely.data.model.dto.AdminDTO;
import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.service.AdminService;
import com.metrica.vibely.data.service.UserService;
import com.metrica.vibely.model.request.CreateAdminRequest;
import com.metrica.vibely.model.request.CreateUserRequest;
import com.metrica.vibely.model.request.UpdateAdminRequest;
import com.metrica.vibely.model.request.UpdateUserRequest;
import com.metrica.vibely.model.response.BasicInfoResponse;
import com.metrica.vibely.model.response.CreateAdminResponse;
import com.metrica.vibely.model.response.CreateUserResponse;

import jakarta.validation.Valid;

/**
 * @since 2023-11-14
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    // <<-FIELDS->>
    private UserService userService;

    // <<-CONSTRUCTOR->>
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // <<-METHODS->>
    @GetMapping("/{id}")
    public ResponseEntity<BasicInfoResponse> getById(@PathVariable UUID id) {
        UserDTO userDTO = this.userService.getById(id);
        
        if (userDTO.getState() != UserState.DISABLED) {
            return ResponseEntity.ok()
                    .body(new BasicInfoResponse().generateResponse(userDTO));
        }
        
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<BasicInfoResponse> getByUsername(@PathVariable String username) {
        UserDTO userDTO = this.userService.getByUsername(username);
        
        if (userDTO.getState()   != UserState.DISABLED &&
            userDTO.getPrivacy() == PrivacyType.PUBLIC) {
            return ResponseEntity.ok().body(new BasicInfoResponse().generateResponse(userDTO));
        }
        
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<CreateUserResponse> create(
            @RequestBody
            @Valid
            CreateUserRequest userRequest,
            BindingResult bindingResult
    ) {
        
        if (bindingResult.hasErrors()) {
            // TODO: This is only for debug the validation errors. DELETE LATER!!
            bindingResult.getAllErrors().forEach(System.err::println);
            
            return ResponseEntity.badRequest().build();
        }
        
        UserDTO userDTO = this.userService.create(userRequest.toUserDTO());
        return ResponseEntity.ok()
                .body(new CreateUserResponse().generateResponse(userDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateUserResponse> updateById(
            @PathVariable
            UUID id,
            @RequestBody
            @Valid
            UpdateUserRequest userRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        
        UserDTO userDTO = userRequest.toDTO();
        userDTO.setUserId(id);
        
        UserDTO updatedDTO = this.userService.update(userDTO);
        return ResponseEntity.ok()
                .body(new CreateUserResponse().generateResponse(updatedDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        this.userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
