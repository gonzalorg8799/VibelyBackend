package com.metrica.vibely.data.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.State;
import com.metrica.vibely.data.model.enumerator.Status;
import com.metrica.vibely.data.util.PasswordHashing;

/**
 * <h1>User Entity Test</h1>
 * 
 * @since 2023-11-16
 * @version 1.0
 * @author Daniel
 */
@SpringBootTest
public class UserRepositoryTests {

    // <<-FIELD->>
    private UserRepository userRepository;
    
    // <<-CONSTRUCTOR->>
    @Autowired
    public UserRepositoryTests(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    

    // <<-METHODS->>
    private User initializeUser(String username, String password, String nickname, String email) {
    	User user = new User();
    	user.setUserId		(null);
    	user.setUsername	(username);
    	user.setPassword	(PasswordHashing.hash(password));
    	user.setNickname	(nickname);
    	user.setEmail   	(email);
    	user.setState       (State.ENABLED);
        user.setPrivacy     (PrivacyType.PUBLIC);
        user.setStatus      (Status.ONLINE);
        user.setLogins      (0);
        user.setLastConnDate(LocalDateTime.now());
        user.setBlockedDate (LocalDate.now());
        user.setFollowers   (new HashSet<>());
        user.setFollowing   (new HashSet<>());
        user.setPosts       (new HashSet<>());
        user.setChats       (new HashSet<>());
		return user;
    }
    
    @Test
    @Order(1)
    void saveFlushDeleteTest() {
    	User testUser1 = initializeUser("jdoe", "jdoe", "Jhon Doe", "johndoe@email.com");
    	userRepository.saveAndFlush(testUser1);
    	User testUser2 = initializeUser("test2", "testing2", "Test Account2", "irrelevant@email.com");
    	User testUser3 = initializeUser("test3", "testing3", "Test Account3", "notimportant@email.com");
    	User testUser4 = initializeUser("test4", "testing4", "Test Account4", "deletelater@email.com");
    	Collection<User> collection = new ArrayList<User>();
    	collection.add(testUser2);
    	collection.add(testUser3);
    	collection.add(testUser4);
    	
    	//crear tests assertEquals
    	userRepository.saveAllAndFlush(collection);
    	assertEquals(testUser1, userRepository.findByUsername(testUser1));
    	assertEquals(testUser2, userRepository.findByUsername(testUser2));
    	assertEquals(testUser3, userRepository.findByUsername(testUser3));
    	assertEquals(testUser4, userRepository.findByUsername(testUser4));
    	userRepository.deleteByUsername(testUser1.getUsername());
        assertTrue(userRepository.findByUsername(testUser1.getUsername()).isEmpty());
    	userRepository.deleteAll(collection);
        assertTrue(userRepository.findByUsername(testUser2.getUsername()).isEmpty());
        assertTrue(userRepository.findByUsername(testUser3.getUsername()).isEmpty());
        assertTrue(userRepository.findByUsername(testUser4.getUsername()).isEmpty());
    }
    
    @Test
    @Order(2)
    void findByUsernameTest() {
    	User user = initializeUser("jdoe", "jdoe", "Jhon Doe", "johndoe@email.com");
    	userRepository.saveAndFlush(user);
    	
    	Optional<User> result = userRepository.findByUsername(user.getUsername());
    	User searchedUser = result.get();
    	
    	assertInstanceOf(User.class, result.get());
    	assertEquals(user.getUserId(),      searchedUser.getUserId());
        assertEquals(user.getUsername(),    searchedUser.getUsername());
        assertEquals(user.getPassword(),    searchedUser.getPassword());
        assertEquals(user.getNickname(),    searchedUser.getNickname());
        assertEquals(user.getEmail(),       searchedUser.getEmail());
        assertEquals(user.getState(),       searchedUser.getState());
        assertEquals(user.getStatus(),      searchedUser.getStatus());
        assertEquals(user.getLogins(),      searchedUser.getLogins());
        assertEquals(user.getBlockedDate(), searchedUser.getBlockedDate());
        assertEquals(user.getFollowers(),   searchedUser.getFollowers());
        assertEquals(user.getFollowing(),   searchedUser.getFollowing());
        assertEquals(user.getChats(),       searchedUser.getChats());
        
        userRepository.delete(user);
        assertTrue(userRepository.findByUsername(user.getUsername()).isEmpty());
    }
}
