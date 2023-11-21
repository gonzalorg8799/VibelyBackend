package com.metrica.vibely.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.model.enumerator.ChatStatus;
import com.metrica.vibely.data.model.enumerator.ChatType;
import com.metrica.vibely.data.repository.UserRepository;
import com.metrica.vibely.data.util.PasswordHasher;

/**
 * @since 2023-11-20
 * @author Raul 
 * @version 1.0
 */
public class ChatServiceTest {
	// <<-CONSTANTS->>
    private static final String DEFAULT_USERNAME = "jdoe";
    private static final String DEFAULT_PASSWORD = "jdoe";
    private static final String DEFAULT_NICKNAME = "John Doe";
    private static final String DEFAULT_EMAIL    = "johndoe@email.com";

    // <<-FIELD->>
    private ChatService chatService;
    private UserService userService;
    
    // <<-CONSTRUCTOR->>
    @Autowired
    public ChatServiceTest(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }
    
    // <<-Methods->>
    private UserDTO generateTestUser() {
        UserDTO testUser = new UserDTO();
        
        testUser.setUsername(DEFAULT_USERNAME);
        testUser.setPassword(PasswordHasher.hash(DEFAULT_PASSWORD));
        testUser.setNickname(DEFAULT_NICKNAME);
        testUser.setEmail   (DEFAULT_EMAIL);
        
        return testUser;
    }
    
    UserDTO alex 	= generateTestUser();
    UserDTO gonzalo = generateTestUser();
    UserDTO adri 	= generateTestUser();
    UserDTO dani	= generateTestUser();
    UserDTO raul	= generateTestUser();
    UserDTO pedro	= generateTestUser();
    UserDTO nonExistantUser = generateTestUser();
    
    Set<UserDTO> participants = new java.util.HashSet<>();
    
    @BeforeEach
    void setup() {	
    	participants.add(adri);
    	participants.add(dani);
    	participants.add(raul);
    	
    	alex   .setUsername("alex");
    	gonzalo.setUsername("gonzalo");
    	adri   .setUsername("adri");
    	dani   .setUsername("dani");
    	raul   .setUsername("raul");
    	pedro  .setUsername("pedro");
    	
    	userService.create(alex);
    	userService.create(gonzalo);
    	userService.create(adri);
    	userService.create(dani);
    	userService.create(raul);
    	userService.create(pedro);
    }
    @Test
    void directMessageCreationTest() {
    	Chat testChat = chatService.create(alex.getUserId(), gonzalo);
    	String name   = testChat.getParticipants().stream().map(p -> p.getUsername()).collect(Collectors.joining());
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	
    	assertNotNull(testChat.getChatId());
    	
    	assertEquals(2, 					  testChat.getParticipants().size());
    	assertEquals(0, 					  testChat.getMessages().size());
    	assertEquals(name, 					  testChat.getTitle());
    	assertEquals(ChatType.DIRECT_MESSAGE, testChat.getType());
    	assertEquals(ChatStatus.ACTIVE, 	  testChat.getStatus());
    	assertEquals(LocalDateTime.now().format(formatter), testChat.getLastActivity());
    	assertEquals(LocalDateTime.now().format(formatter), testChat.getCreationDate());
    	
    	assertTrue(testChat		.getParticipants().contains(alex   .getUserId()));
    	assertTrue(testChat		.getParticipants().contains(gonzalo.getUserId()));
    }

    
}
