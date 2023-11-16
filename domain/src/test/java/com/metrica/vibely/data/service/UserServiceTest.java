package com.metrica.vibely.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.model.enumerator.State;
import com.metrica.vibely.data.model.enumerator.Status;
import com.metrica.vibely.data.util.PasswordHashing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <h1>User Service Test</h1>
 * 
 * @since 2023-11-15
 * @version 1.0
 * @author Alex, Adri
 */
public class UserServiceTest {

    // <<-CONSTANTS->>
    private static final String DEFAULT_USERNAME = "jdoe";
    private static final String DEFAULT_PASSWORD = "jdoe";
    private static final String DEFAULT_NICKNAME = "John Doe";
    private static final String DEFAULT_EMAIL    = "johndoe@email.com";

    // <<-FIELD->>
    private UserService userService;
    
    // <<-CONSTRUCTOR->>
    @Autowired
    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }
    
    // <<-METHODS->>
    private UserDTO generateTestUser() {
        UserDTO testUser = new UserDTO();
        
        testUser.setUsername(DEFAULT_USERNAME);
        testUser.setPassword(PasswordHashing.hash(DEFAULT_PASSWORD));
        testUser.setNickname(DEFAULT_NICKNAME);
        testUser.setEmail   (DEFAULT_EMAIL);
        
        return testUser;
    }
    
    @Test
    @Order(1)
    void userCreationTest() {
        UserDTO testUser = generateTestUser();
        UserDTO databaseUser = userService.create(testUser);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        assertNotNull(databaseUser.getUserId());
        assertEquals(LocalDate.now().format(formatter), databaseUser.getLastConnDate().format(formatter));
        assertTrue  (PasswordHashing.matches(DEFAULT_PASSWORD, databaseUser.getPassword()));
        assertEquals(testUser.getUsername(), databaseUser.getUsername());
        assertEquals(testUser.getNickname(), databaseUser.getNickname());
        assertEquals(testUser.getEmail(),    databaseUser.getEmail());
        assertEquals(State.ENABLED,           databaseUser.getState());
        assertEquals(Status.ONLINE,          databaseUser.getStatus());
        assertEquals(1,                      databaseUser.getLogins());
        assertNull  (databaseUser.getBlockedDate());
        assertNull  (databaseUser.getFollowers());
        assertNull  (databaseUser.getFollowing());
        assertNull  (databaseUser.getPosts());
        assertFalse (databaseUser.getChats().isEmpty());
        assertEquals(1, databaseUser.getChats().size());
        
        assertEquals(testUser, databaseUser);
    }
    
    @Test
    @Order(2)
    void userReadTest() {
        UserDTO testUser = generateTestUser();
        UserDTO databaseUser = userService.create(testUser);
        UserDTO searchedUser = userService.getByUsername(testUser.getUsername());
        
        assertEquals(databaseUser.getUserId(),      searchedUser.getUserId());
        assertEquals(databaseUser.getUsername(),    searchedUser.getUsername());
        assertEquals(databaseUser.getPassword(),    searchedUser.getPassword());
        assertEquals(databaseUser.getNickname(),    searchedUser.getNickname());
        assertEquals(databaseUser.getEmail(),       searchedUser.getEmail());
        assertEquals(databaseUser.getState(),       searchedUser.getState());
        assertEquals(databaseUser.getStatus(),      searchedUser.getStatus());
        assertEquals(databaseUser.getLogins(),      searchedUser.getLogins());
        assertEquals(databaseUser.getBlockedDate(), searchedUser.getBlockedDate());
        assertEquals(databaseUser.getFollowers(),   searchedUser.getFollowers());
        assertEquals(databaseUser.getFollowing(),   searchedUser.getFollowing());
        assertEquals(databaseUser.getChats(),       searchedUser.getChats());

        assertEquals(databaseUser, searchedUser);
    }
    
    @Test
    @Order(3)
    void userUpdateTest() {
    	UserDTO createdUser 	= userService.create(generateTestUser());
        UserDTO nonExistingUser = userService.create(generateTestUser());
        UserDTO updatedUser 	= userService.create(generateTestUser());
        
        UUID createdUserUUID 	 = createdUser.getUserId();
        UUID nonExistingUserUUID = nonExistingUser.getUserId();
        
        String newUsername  	= "New Username";
        String newNickname  	= "Updated Nickname";
        String newEmail     	= "newEmail@email.com";
        String newPassword  	= "NewPassword";
        
        updatedUser.setNickname (newUsername);
        updatedUser.setUsername (newUsername);
        updatedUser.setEmail	(newEmail);
        updatedUser.setPassword (PasswordHashing.hash(newPassword));
        
        userService.updateNickname	(createdUser.getUserId(), newNickname);
        userService.updateUsername	(createdUserUUID, newUsername);
        userService.updateEmail	  	(createdUserUUID, newEmail);
        userService.updatePassword	(createdUserUUID, newPassword);
        userService.deleteByUsername(nonExistingUser.getUsername());

        //Basic
        assertEquals(newUsername, createdUser.getUsername());
        assertEquals(newNickname, createdUser.getNickname());
        assertEquals(newEmail, createdUser.getEmail());
        assertEquals(PasswordHashing.hash(newPassword), createdUser.getPassword());
        assertEquals(createdUser, updatedUser);

        //User not exist
        assertThrows(NoSuchElementException.class, () -> userService.updateUsername(nonExistingUserUUID, newUsername));
        assertThrows(NoSuchElementException.class, () -> userService.updateNickname(nonExistingUserUUID, newNickname));
        assertThrows(NoSuchElementException.class, () -> userService.updateEmail(nonExistingUserUUID, newEmail));
        assertThrows(NoSuchElementException.class, () -> userService.updatePassword(nonExistingUserUUID, newPassword));
    }
    
    @Test
    @Order(4)
    void userDeleteTest() {
        UserDTO testUser 	= generateTestUser();
        UserDTO testUser2 	= generateTestUser();
        UserDTO createdUser = userService.create(testUser);

        userService.deleteByUsername(createdUser.getUsername());
        
        assertThrows(NoSuchElementException.class, () -> userService.deleteByUsername(testUser2.getUsername()));
    }
    
    @Test
    @Order(5)
    void userFollowTest() {
    	
    }
    
}
