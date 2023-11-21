package com.metrica.vibely.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

/**
 * <h1>Import Controller</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Alex
 */
@RestController
@RequestMapping("api/v1/import")
public class ImportController {

    // <<-CONSTANTS->>
    private static final String SRC = "";

    // <<-METHODS->>
    private ResponeEntity<?> getData(ImportAuthRequest authRquest) {
        
    }
    
    @PostMapping("/data")
    public ResponseEntity<?> getUserInfo(
            @RequestBody
            @Valid
            ImportAuthRequest authRquest,
            BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return getData(authRquest);
        }
        return ResponseEntity.badRequest().build();
    }
    
}