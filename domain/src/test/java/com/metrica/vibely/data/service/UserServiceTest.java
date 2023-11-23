package com.metrica.vibely.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.model.enumerator.UserStatus;
import com.metrica.vibely.data.model.mapper.UserMapper;
import com.metrica.vibely.data.repository.UserRepository;

import com.metrica.vibely.data.util.PasswordHasher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <h1>User Service Test</h1>
 * 
 * @since 2023-11-15
 * @version 1.0
 * @author Alex, Adri, Raul
 */
@SpringBootTest
public class UserServiceTest {

    // <<-CONSTANTS->>
    private static final String DEFAULT_USERNAME = "jdoe";
    private static final String DEFAULT_PASSWORD = "jdoe";
    private static final String DEFAULT_NICKNAME = "John Doe";
    private static final String DEFAULT_EMAIL    = "johndoe@email.com";

    // <<-FIELD->>
    private UserService userService;
    private UserRepository userRepository;
    
    // <<-CONSTRUCTOR->>
    @Autowired
    public UserServiceTest(UserService userService, UserRepository userRepository) {
        this.userService 	= userService;
        this.userRepository = userRepository;
    }
    
    // <<-METHODS->>
    private UserDTO generateTestUser() {
        UserDTO testUser = new UserDTO();
        
        testUser.setUsername(DEFAULT_USERNAME);
        testUser.setPassword(PasswordHasher.hash(DEFAULT_PASSWORD));
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
        assertTrue  (PasswordHasher.matches(DEFAULT_PASSWORD, databaseUser.getPassword()));
        assertEquals(testUser.getUsername(), databaseUser.getUsername());
        assertEquals(testUser.getNickname(), databaseUser.getNickname());
        assertEquals(testUser.getEmail(),    databaseUser.getEmail());
        assertEquals(UserState.ENABLED,           databaseUser.getState());
        assertEquals(UserStatus.ONLINE,          databaseUser.getStatus());
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
    	UserDTO updatedDataDTO  = userService.create(generateTestUser());
        UserDTO nonExistingUser = userService.create(generateTestUser());
        
        String newUsername  	= "New Username";
        String newNickname  	= "Updated Nickname";
        String newEmail     	= "newEmail@email.com";
        String newPassword  	= "NewPassword";
        
        updatedDataDTO.setNickname(newNickname);
        updatedDataDTO.setUsername(newUsername);
        updatedDataDTO.setEmail(newEmail);
        updatedDataDTO.setPassword(newPassword);
        
        userService.update(updatedDataDTO);
        
        userService.deleteByUsername(nonExistingUser.getUsername());
        
        UserDTO updatedUser = UserMapper.toDTO(userRepository.findByUsername(createdUser.getUsername()).get());
        
        //Basic
        assertEquals(newUsername, 						updatedUser.getUsername());
        assertEquals(newNickname, 						updatedUser.getNickname());
        assertEquals(newEmail, 							updatedUser.getEmail());
        assertEquals(PasswordHasher.hash(newPassword), updatedUser.getPassword());
        
        assertNotEquals(newUsername, 						createdUser.getUsername());
        assertNotEquals(newNickname, 						createdUser.getNickname());
        assertNotEquals(newEmail, 							createdUser.getEmail());
        assertNotEquals(PasswordHasher.hash(newPassword),  createdUser.getPassword());
        
        //User not exist
        assertThrows(NoSuchElementException.class, () -> userService.update(nonExistingUser));
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
    	UserDTO createdUser 	= userService.create(generateTestUser());
    	UserDTO follower1 		= userService.create(generateTestUser());
    	UserDTO follower2 		= userService.create(generateTestUser());
    	UserDTO follower3 		= userService.create(generateTestUser());
    	
    	// No followers
    	assertEquals(0, createdUser.getFollowers().size());
    	assertEquals(0, follower1  .getFollowers().size());
    	assertEquals(0, follower1  .getFollowers().size());
    	
    	assertNull(createdUser.getFollowers());
    	assertNull(follower1  .getFollowers());
    	assertNull(follower2  .getFollowers());
    	
    	//No followed
    	assertEquals(0, createdUser.getFollowing().size());
    	assertEquals(0, follower1  .getFollowing().size());
    	assertEquals(0, follower1  .getFollowing().size());
    	
    	assertNull(createdUser.getFollowing());
    	assertNull(follower1  .getFollowing());
    	assertNull(follower2  .getFollowing());
    	
    	//After following
    	userService.followUser(follower2.getUserId(), createdUser.getUserId());
    	userService.followUser(follower1.getUserId(), createdUser.getUserId());
    	userService.followUser(follower1.getUserId(), follower2  .getUserId());
    	userService.followUser(follower2.getUserId(), follower1  .getUserId());
    	
    	createdUser = UserMapper.toDTO(userRepository.findById(createdUser.getUserId()).get());
    	follower1 	= UserMapper.toDTO(userRepository.findById(follower1  .getUserId()).get());
    	follower2 	= UserMapper.toDTO(userRepository.findById(follower2  .getUserId()).get());
    	
    	assertEquals(2, createdUser.getFollowers().size());
    	assertEquals(0, createdUser.getFollowing().size());
    	assertEquals(1, follower1  .getFollowing().size());
    	assertEquals(1, follower1  .getFollowers().size());
    	assertEquals(2, follower1  .getFollowing().size());
    	assertEquals(2, follower2  .getFollowing().size());
    	
    	//After unfollowing
    	userService.unfollowUser(follower1.getUserId(), follower2  .getUserId());
    	userService.unfollowUser(follower2.getUserId(), follower1  .getUserId());
    	userService.unfollowUser(follower1.getUserId(), createdUser.getUserId());
    	
    	createdUser = UserMapper.toDTO(userRepository.findById(createdUser.getUserId()).get());
    	follower1 	= UserMapper.toDTO(userRepository.findById(follower1  .getUserId()).get());
    	follower2 	= UserMapper.toDTO(userRepository.findById(follower2  .getUserId()).get());
    	
    	assertEquals(0, follower2  .getFollowers().size());
    	assertEquals(0, follower1  .getFollowers().size());
    	assertEquals(1, createdUser.getFollowers().size());
    	assertEquals(1, follower1  .getFollowing().size());
    	assertEquals(1, follower2  .getFollowing().size());
    	assertEquals(0, createdUser.getFollowing().size());

    	//Following non existing user | Non existing user tries to follow
    	UserDTO existingUser = userService.create(generateTestUser());
    	
    	userService.deleteByUsername(follower3.getUsername());
    	assertThrows(NoSuchElementException.class, () -> userService.followUser(existingUser.getUserId(), follower3   .getUserId()));
    	assertThrows(NoSuchElementException.class, () -> userService.unfollowUser(follower3 .getUserId(), existingUser.getUserId()));
    	
    	//Following itself
    	int compareFollowerSize = createdUser.getFollowers().size();
    	int compareFollowingSize = createdUser.getFollowers().size();
    	
    	userService.followUser(createdUser.getUserId(), createdUser.getUserId());
    	
    	createdUser = UserMapper.toDTO(userRepository.findById(createdUser.getUserId()).get());
    	
    	assertEquals(compareFollowerSize, createdUser.getFollowers().size());
    	assertEquals(compareFollowingSize, createdUser.getFollowing().size());
    }
    
}
