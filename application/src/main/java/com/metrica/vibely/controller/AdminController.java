package com.metrica.vibely.controller;

import com.metrica.vibely.controller.util.ResponseManager;
import com.metrica.vibely.data.model.dto.AdminDTO;
import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.model.request.CreateAdminRequest;
import com.metrica.vibely.model.request.UpdateAdminRequest;
import com.metrica.vibely.model.response.create.CreateAdminResponse;
import com.metrica.vibely.model.response.get.BasicInfoResponse;
import com.metrica.vibely.model.response.update.UpdateAdminResponse;
import com.metrica.vibely.service.AdminService;

import jakarta.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>Administrator Controller</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Alex
 */
@RestController
@RequestMapping("/api/v1/admin/users")
public class AdminController {

    // <<-FIELD->>
    private ResponseManager responseManager;
    private AdminService adminService;

    // <<-CONSTRUCTOR->>
    @Autowired
    public AdminController(
            ResponseManager responseManager,
            AdminService    adminService) {
        this.responseManager = responseManager; 
        this.adminService    = adminService;
    }

    // <<-METHODS->>
    @GetMapping("/{id}")
    public ResponseEntity<BasicInfoResponse> getById(@PathVariable UUID id) {
        AdminDTO adminDTO = this.adminService.getById(id);
        return this.responseManager.generateGetResponse(adminDTO);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<BasicInfoResponse> getByUsername(@PathVariable String username) {
        AdminDTO adminDTO = this.adminService.getByUsername(username);
        
        if (adminDTO.getState()   != UserState.DISABLED &&
            adminDTO.getPrivacy() == PrivacyType.PUBLIC) {
        	return this.responseManager.generateGetResponse(adminDTO);
        }
        
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<BasicInfoResponse> getByEmail(@PathVariable String email) {
        AdminDTO adminDTO = this.adminService.getByEmail(email);
        
        if (adminDTO.getState()   != UserState.DISABLED &&
            adminDTO.getPrivacy() == PrivacyType.PUBLIC) {
        	return this.responseManager.generateGetResponse(adminDTO);
        }
        
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<CreateAdminResponse> create(
            @RequestBody
            @Valid
            CreateAdminRequest userRequest,
            BindingResult bindingResult
    ) {
        
        if (bindingResult.hasErrors()) {
            // TODO: This is only for debug the validation errors. DELETE LATER!!
            bindingResult.getAllErrors().forEach(System.err::println);
            
            return ResponseEntity.badRequest().build();
        }
        
        AdminDTO adminDTO = this.adminService.create(userRequest.toAdminDTO());
        return this.responseManager.generateCreateResponse(adminDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateAdminResponse> updateById(
            @PathVariable
            UUID id,
            @RequestBody
            @Valid
            UpdateAdminRequest userRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        
        AdminDTO adminDTO = userRequest.toDTO();
        adminDTO.setUserId(id);
        
        AdminDTO updatedDTO = this.adminService.update(adminDTO);
        return this.responseManager.generateUpdateResponse(updatedDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        this.adminService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}