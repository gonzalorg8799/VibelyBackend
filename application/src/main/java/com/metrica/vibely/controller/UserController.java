package com.metrica.vibely.controller;

import com.metrica.vibely.controller.util.ResponseManager;
import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.service.UserService;
import com.metrica.vibely.model.request.CreateUserRequest;
import com.metrica.vibely.model.request.UpdateUserRequest;
import com.metrica.vibely.model.response.create.CreateUserResponse;
import com.metrica.vibely.model.response.get.BasicInfoResponse;
import com.metrica.vibely.model.response.get.GetFriendNetworkResponse;
import com.metrica.vibely.model.response.update.UpdateUserResponse;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

/**
 * <h1>User Controller</h1>
 * 
 * @since 2023-11-14
 * @version 1.0
 * @author Gonzalo, Adrian, Daniel
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    // <<-FIELDS->>
	private ResponseManager responseManager;
    private UserService userService;

    // <<-CONSTRUCTOR->>
    @Autowired
    public UserController(UserService userService, ResponseManager responseManager) {
    	this.responseManager = responseManager;
        this.userService = userService;
    }

    // <<-METHODS->>
    @GetMapping("/{id}")
    public ResponseEntity<BasicInfoResponse> getById(@PathVariable UUID id) {
        
        UserDTO userDTO = this.userService.getById(id);
        
        if (userDTO.getState() != UserState.DISABLED) {
        	return this.responseManager.generateGetResponse(userDTO);
        }
        
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<BasicInfoResponse> getByUsername(@PathVariable String username) {
        UserDTO userDTO = this.userService.getByUsername(username);
        
        if (userDTO.getState()   != UserState.DISABLED &&
            userDTO.getPrivacy() == PrivacyType.PUBLIC) {
        	return this.responseManager.generateGetResponse(userDTO);
        }
        
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<BasicInfoResponse> getByEmail(@PathVariable String email) {
        UserDTO userDTO = this.userService.getByEmail(email);
        
        if (userDTO.getState()   != UserState.DISABLED &&
            userDTO.getPrivacy() == PrivacyType.PUBLIC) {
        	return this.responseManager.generateGetResponse(userDTO);
        }
        
        return ResponseEntity.notFound().build();
    }
    
    /**
     * @TODO falta añadir la funcionalidad
     * @param id
     * @return
     */
    @GetMapping("/friendNetwork/{id}")
    public ResponseEntity<GetFriendNetworkResponse> getNetwork(@PathVariable UUID id){

		this.userService.getFriendNetwork(id);

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
            return ResponseEntity.badRequest().build();
        }
        
        UserDTO userDTO = this.userService.create(userRequest.toUserDTO());
        return this.responseManager.generateCreateResponse(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserResponse> updateById(
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
        return this.responseManager.generateUpdateResponse(updatedDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        this.userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}