package com.metrica.vibely.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrica.vibely.model.User;
import com.metrica.vibely.model.request.CreateUserRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService= userService;
	}
	@GetMapping("/obtenerUsuario/{username}")
	public User obtenerUsuario(@PathVariable String username) {
		return this.userService.getByUsername(username);
	}

	@PostMapping("/crearUsuario")	
	public ResponseEntity<?> crearUsuario(@RequestBody @Valid CreateUserRequest createUser, BindingResult bindingResult) {
		if(!bindingResult.getAllErrors().isEmpty())return (ResponseEntity<?>) ResponseEntity.badRequest();
		return (ResponseEntity<?>) ResponseEntity.created(null);
		//createUser.getUsername()
	}
}
