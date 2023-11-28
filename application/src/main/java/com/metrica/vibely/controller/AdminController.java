package com.metrica.vibely.controller;

import com.metrica.vibely.controller.util.ResponseManager;
import com.metrica.vibely.data.model.dto.AdminDTO;
import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.service.AdminService;
import com.metrica.vibely.model.request.CreateAdminRequest;
import com.metrica.vibely.model.request.UpdateAdminRequest;
import com.metrica.vibely.model.response.BasicInfoResponse;
import com.metrica.vibely.model.response.CreateAdminResponse;

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
        return this.responseManager.generateResponse(adminDTO);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<BasicInfoResponse> getByUsername(@PathVariable String username) {
        AdminDTO adminDTO = this.adminService.getByUsername(username);
        
        if (adminDTO.getState()   != UserState.DISABLED &&
            adminDTO.getPrivacy() == PrivacyType.PUBLIC) {
            return ResponseEntity.ok().body(new BasicInfoResponse().generateResponse(adminDTO));
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
        return ResponseEntity.ok()
                .body(new CreateAdminResponse().generateResponse(adminDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(
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
        return ResponseEntity.ok()
                .body(new CreateAdminResponse().generateResponse(updatedDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        this.adminService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}