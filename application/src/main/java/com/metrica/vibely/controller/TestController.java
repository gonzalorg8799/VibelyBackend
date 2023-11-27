package com.metrica.vibely.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.repository.UserRepository;
import com.metrica.vibely.data.service.UserService;
import com.metrica.vibely.model.request.CreateUserRequest;
import com.metrica.vibely.test.Parser;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {
	private UserRepository userRepository;
	@Autowired
	public TestController(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	@PostMapping("/signup")
    public ResponseEntity<UserDTO> crearUsuario(
            @RequestBody
            CreateUserRequest createUser,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().build();
//        UserDTO userDto = CreateUserRequest.toUserDTO(createUser);
        UserDTO userDto = new CreateUserRequest().toUserDTO();
        userRepository.save(Parser.toEntity(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
	@GetMapping("/{username}")
    public UserDTO getUserByUsername(@PathVariable String username) {
		Parser parser = new Parser();
        return parser.toUserDTO2(userRepository.findByUsername(username).get());
    }
	@GetMapping("/find")
    public List<UserDTO> getUserByUsername() {
		Parser parser = new Parser();
		return userRepository.findAll().stream().map(u->parser.toUserDTO2(u)).collect(Collectors.toList());
    }
}
