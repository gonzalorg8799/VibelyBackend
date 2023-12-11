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
import com.metrica.vibely.data.model.dto.ChatDTO;
import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.model.enumerator.ChatStatus;
import com.metrica.vibely.data.model.enumerator.ChatType;
import com.metrica.vibely.data.repository.UserRepository;
import com.metrica.vibely.data.util.PasswordHasher;
import com.metrica.vibely.service.ChatService;
import com.metrica.vibely.service.UserService;

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
//    	Chat testChat = chatService.create(alex.getUserId(), gonzalo);
//    	String name   = testChat.getParticipants().stream().map(p -> p.getUsername()).collect(Collectors.joining());
//    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    	
//    	assertNotNull(testChat.getChatId());
//    	
//    	assertEquals(2, 					  testChat.getParticipants().size());
//    	assertEquals(0, 					  testChat.getMessages().size());
//    	assertEquals(name, 					  testChat.getTitle());
//    	assertEquals(ChatType.DIRECT_MESSAGE, testChat.getType());
//    	assertEquals(ChatStatus.ACTIVE, 	  testChat.getStatus());
//    	assertEquals(LocalDateTime.now().format(formatter), testChat.getLastActivity());
//    	assertEquals(LocalDateTime.now().format(formatter), testChat.getCreationDate());
//    	
//    	assertTrue(testChat		.getParticipants().contains(alex   .getUserId()));
//    	assertTrue(testChat		.getParticipants().contains(gonzalo.getUserId()));
    }
    @Test
    void groupMessageCreationTest() {
//    	ChatDTO testChat = chatService.create(alex.getUserId(), participants);
//    	String name   = testChat.getParticipants().stream().map(p -> p.getUsername()).collect(Collectors.joining());
//    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	
//    	UserRepository repo = new UserRepository();
    	
    	
//    	assertNotNull(testChat.getChatId());
//    	
//    	assertEquals(3, 					  testChat.getParticipants().size());
//    	assertEquals(0, 					  testChat.getMessages().size());
//    	assertEquals(name, 					  testChat.getTitle());
//    	assertEquals(ChatType.GROUP, 		  testChat.getType());
//    	assertEquals(ChatStatus.ACTIVE, 	  testChat.getStatus());
//    	assertEquals(LocalDateTime.now().format(formatter), testChat.getLastActivity());
//    	assertEquals(LocalDateTime.now().format(formatter), testChat.getCreationDate());
//    	
//    	assertTrue(testChat		.getParticipants().contains(alex   .getUserId()));
//    	assertTrue(testChat		.getParticipants().contains(gonzalo.getUserId()));
//    	assertTrue(testChat		.getParticipants().contains(adri   .getUserId()));
    }
    @Test
    void chatParticipantsManipulation() {
//    	ChatDTO mdChat = chatService.create(alex.getUserId(), gonzalo.getUserId());
//    	
//    	//Adding non participating users
//    	mdChat = mdChat.chatService.addParticipant(mdChat.getChatId(), pedro.getUserId());
//    	mdChat = mdChat.chatService.addParticipant(mdChat.getChatId(), participants);
//    	
//    	String partNames = "alex gonzalo pedro adri dani raul";
//    	String partNamesCheck = mdChat.getParticipants().stream().map(p -> p.getUsername()).reduce(" ");
//    	
//    	AssertEquals(6, mdChat.getParticipants().size());
//    	
//    	AssertTrue(mdChat.getParticipants().contains(alex));
//    	AssertTrue(mdChat.getParticipants().contains(gonzalo));
//    	AssertTrue(mdChat.getParticipants().contains(adri));
//    	AssertTrue(mdChat.getParticipants().contains(dani));
//    	AssertTrue(mdChat.getParticipants().contains(raul));
//    	AssertTrue(mdChat.getParticipants().contains(pedro));
//    	
//    	//Adding already participating users
//    	mdChat = mdChat.chatService.addParticipant(mdChat.getChatId() ,adri.getUserId());
//    	mdChat = mdChat.chatService.addParticipant(mdChat.getChatId() ,dani.getUserId());
//    	
//    	AssertEquals(5, mdChat.getParticipants().size());
//    	
//    	AssertTrue(mdChat.getParticipants().contains(alex));
//    	AssertTrue(mdChat.getParticipants().contains(gonzalo));
//    	AssertTrue(mdChat.getParticipants().contains(adri));
//    	AssertTrue(mdChat.getParticipants().contains(dani));
//    	AssertTrue(mdChat.getParticipants().contains(raul));
//    	AssertTrue(mdChat.getParticipants().contains(pedro));
//    	
//    	//Adding non existant users
//    	
//    	assertThrows(NoSuchElementException.class, () -> chatService.addParticipant(nonExistingUser));
//    	
//    	//Remove participants
//    	mdChat = mdChat.chatService.removeParticipant(mdChat.getChatId() ,alex.getUserId());
//    	mdChat = mdChat.chatService.removeParticipant(mdChat.getChatId() ,gonzalo.getUserId(), adri.getUserId());
//    	
//    	AssertEquals(2,    mdChat.chatService.getParticipants().size());
//    	AssertEquals(raul, mdChat.chatService.getParticipantById(raul.getUserId()));
//    	AssertEquals(raul, mdChat.chatService.removeParticipant(mdChat.getChatId() ,raul.getUserId()));
//    	
//    	AssertFalse(mdChat.getParticipants().contains(alex));
//    	AssertFalse(mdChat.getParticipants().contains(gonzalo));
//    	AssertFalse(mdChat.getParticipants().contains(adri));
    }
}
