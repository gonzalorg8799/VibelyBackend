package com.metrica.vibely.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrica.vibely.service.DiscordService;

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

    // <<-FIELD->>
    DiscordService discordService;
    
    // <<-CONSTRUCTOR->>
    @Autowired
    public ImportController(DiscordService discordService) {
        this.discordService = discordService;
    }

    // <<-METHODS->>
    @PostMapping("/discord/data")
    public ResponseEntity<?> getUserInfo() {
        return ResponseEntity.ok().body(discordService.getUserInfo(null));
    }
    
}