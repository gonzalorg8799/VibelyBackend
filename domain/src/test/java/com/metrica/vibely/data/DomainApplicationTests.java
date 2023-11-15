package com.metrica.vibely.data;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.model.enumerator.Status;
import com.metrica.vibely.data.model.mapper.UserMapper;
import com.metrica.vibely.data.service.UserService;
import com.metrica.vibely.data.util.PasswordHashing;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;;

/**
 * 
 * 
 * @since 2023-11-15
 * @version 1.0
 * @author Alex, Adri
 */
@SpringBootTest
class DomainApplicationTests {

    // <<-CONSTANTS->>
    private static final String DEFAULT_USERNAME = "jdoe";
    private static final String DEFAULT_PASSWORD = "jdoe";
    private static final String DEFAULT_NICKNAME = "John Doe";
    private static final String DEFAULT_EMAIL    = "johndoe@email.com";
    
    // <<-FIELD->>
    private UserService userService;
    
    // <<-CONSTRUCTOR->>
    @Autowired
    public DomainApplicationTests(UserService userService) {
        this.userService = userService;
    }
    
    // <<-METHODS->>
	@Test
	void contextLoads() {
	}
	
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
        assertEquals(LocalDate.now().format(formatter), databaseUser.getLastConnectionDate().format(formatter));
	    assertTrue  (PasswordHashing.matches(DEFAULT_PASSWORD, databaseUser.getPassword()));
        assertEquals(testUser.getUsername(), databaseUser.getUsername());
        assertEquals(testUser.getNickname(), databaseUser.getNickname());
        assertEquals(testUser.getEmail(),    databaseUser.getEmail());
        assertEquals(State.ACTIVE,           databaseUser.getState());
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
    void userUpdateTest() {
        UserDTO testUser = generateTestUser();
        
    }
	
	@Test
	void userDeleteTest() {
        UserDTO testUser = generateTestUser();
	    
        assertThrows(NoSuchElementException.class, () -> userService.deleteByUsername(testUser.getUsername()));
	}

}
