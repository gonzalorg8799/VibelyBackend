package com.metrica.vibely.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.model.enumerator.UserStatus;
import com.metrica.vibely.data.model.mapper.UserMapper;
import com.metrica.vibely.data.repository.UserRepository;
import com.metrica.vibely.data.service.impl.UserServiceImpl;
import com.metrica.vibely.data.util.PasswordHasher;

/**
 * <h1>User Service Test</h1>
 * 
 * @since 2023-11-15
 * @version 1.0
 * @author Alex, Adri, Raul
 */

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	// <<-CONSTANTS->>
	private static final String DEFAULT_USERNAME = "jdoe";
	private static final String DEFAULT_PASSWORD = "jdoe";
	private static final String DEFAULT_NICKNAME = "John Doe";
	private static final String DEFAULT_EMAIL = "johndoe@email.com";
	private static final String APIKEY = "randomApikey";
	private static final Integer LOGINS = 1;
	private static final UserState STATE = UserState.ENABLED;
	private static final PrivacyType PRIVACY = PrivacyType.PUBLIC;
	private static final UserStatus STATUS = UserStatus.ONLINE;

	// <<-FIELD->>
	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService = new UserServiceImpl(userRepository);

	// <<-CONSTRUCTOR->>
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	private UserDTO generateTestUser() {
		UserDTO testUser = new UserDTO();
		Set<UUID> followers = new HashSet<>();
		Set<UUID> following = new HashSet<>();
		Set<UUID> posts = new HashSet<>();
		Set<UUID> chats = new HashSet<>();
		Set<UUID> likes = new HashSet<>();
		Set<UUID> saves = new HashSet<>();

		testUser.setUserId(UUID.randomUUID());
		testUser.setUsername(DEFAULT_USERNAME);
		testUser.setPassword(PasswordHasher.hash(DEFAULT_PASSWORD));
		testUser.setNickname(DEFAULT_NICKNAME);
		testUser.setEmail(DEFAULT_EMAIL);
		testUser.setApikey(APIKEY);
		testUser.setState(STATE);
		testUser.setPrivacy(PRIVACY);
		testUser.setStatus(STATUS);
		testUser.setLogins(LOGINS);
		testUser.setFollowers(followers);
		testUser.setFollowing(following);
		testUser.setPosts(posts);
		testUser.setChats(chats);
		testUser.setLikes(likes);
		testUser.setSaves(saves);

		return testUser;
	}

	// <<-METHODS->>
	

	@Test
	@Order(1)
	void userCreationTest() {

		UserDTO testUser = generateTestUser();
		when(userRepository.save(UserMapper.toEntity(testUser, null, null, null, null, null, null)))
				.thenReturn(UserMapper.toEntity(testUser, null, null, null, null, null, null));
		UserDTO databaseUser = userService.create(testUser);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		assertNotNull(databaseUser.getUserId());
		assertEquals (LocalDate.now().format(formatter), databaseUser.getLastConnDate().format(formatter));
		assertTrue	 (PasswordHasher.matches(DEFAULT_PASSWORD, databaseUser.getPassword()));
		assertEquals (testUser.getUsername(), databaseUser.getUsername());
		assertEquals (testUser.getNickname(), databaseUser.getNickname());
		assertEquals (testUser.getEmail(), databaseUser.getEmail());
		assertEquals (UserState.ENABLED, databaseUser.getState());
		assertEquals (UserStatus.ONLINE, databaseUser.getStatus());
		assertEquals (1, databaseUser.getLogins());
		assertNull	 (databaseUser.getBlockedDate());
		assertNotNull(databaseUser.getFollowers());
		assertNotNull(databaseUser.getFollowing());
		assertNotNull(databaseUser.getPosts());
		assertNotNull(databaseUser.getChats());
		assertNotNull(databaseUser.getLikes());
		assertNotNull(databaseUser.getSaves());

		assertEquals(testUser, databaseUser);
	}

	@Test
	@Order(2)
	void userReadTest() {
		UserDTO testUser = generateTestUser();
		when(userRepository.save(UserMapper.toEntity(testUser, null, null, null, null, null, null)))
				.thenReturn(UserMapper.toEntity(testUser, null, null, null, null, null, null));
		UserDTO databaseUser = userService.create(testUser);
		when(userRepository.findByUsername(testUser.getUsername()))
				.thenReturn(Optional.of(UserMapper.toEntity(testUser, null, null, null, null, null, null)));
		UserDTO searchedUser = userService.getByUsername(testUser.getUsername());

		assertEquals(databaseUser.getUserId(),  	searchedUser.getUserId());
		assertEquals(databaseUser.getUsername(),	searchedUser.getUsername());
		assertEquals(databaseUser.getPassword(),	searchedUser.getPassword());
		assertEquals(databaseUser.getNickname(),	searchedUser.getNickname());
		assertEquals(databaseUser.getEmail(), 		searchedUser.getEmail());
		assertEquals(databaseUser.getState(), 		searchedUser.getState());
		assertEquals(databaseUser.getStatus(), 		searchedUser.getStatus());
		assertEquals(databaseUser.getLogins(), 		searchedUser.getLogins());
		assertEquals(databaseUser.getBlockedDate(), searchedUser.getBlockedDate());
		assertEquals(databaseUser.getFollowers(), 	searchedUser.getFollowers());
		assertEquals(databaseUser.getFollowing(), 	searchedUser.getFollowing());
		assertEquals(databaseUser.getChats(), 		searchedUser.getChats());
		assertEquals(databaseUser.getLikes(), 		searchedUser.getLikes());
		assertEquals(databaseUser.getSaves(), 		searchedUser.getSaves());

		assertEquals(databaseUser, searchedUser);
	}
    
    @Test
    @Order(3)
    void userUpdateTest() {
    	UserDTO testUser1 = generateTestUser();
    	UserDTO testUser2 = generateTestUser();
    	UserDTO testUser3 = generateTestUser();
    	when(userRepository.save(UserMapper.toEntity(testUser1, null, null, null, null, null, null)))
				.thenReturn(UserMapper.toEntity(testUser1, null, null, null, null, null, null));
    	UserDTO createdUser 	= userService.create(testUser1);
    	when(userRepository.save(UserMapper.toEntity(testUser2, null, null, null, null, null, null)))
				.thenReturn(UserMapper.toEntity(testUser2, null, null, null, null, null, null));
    	UserDTO updatedDataDTO  = userService.create(testUser2);
    	when(userRepository.save(UserMapper.toEntity(testUser3, null, null, null, null, null, null)))
				.thenReturn(UserMapper.toEntity(testUser3, null, null, null, null, null, null));
    	UserDTO nonExistingUser = userService.create(testUser3);
        
        String newUsername  	= "New Username";
        String newNickname  	= "Updated Nickname";
        String newEmail     	= "newEmail@email.com";
        String newPassword  	= "NewPassword";
        
        updatedDataDTO.setNickname	(newNickname);
        updatedDataDTO.setUsername	(newUsername);
        updatedDataDTO.setEmail		(newEmail);
        updatedDataDTO.setPassword	(PasswordHasher.hash(newPassword));
        
        when(userRepository.findById(updatedDataDTO.getUserId()))
				.thenReturn(Optional.of(UserMapper.toEntity(updatedDataDTO, null, null, null, null, null, null)));
        when(userRepository.save(UserMapper.toEntity(updatedDataDTO, null, null, null, null, null, null)))
				.thenReturn(UserMapper.toEntity(updatedDataDTO, null, null, null, null, null, null));
        userService.update(updatedDataDTO);
        
        userService.deleteByUsername(nonExistingUser.getUsername());
        
        when(userRepository.findByUsername(createdUser.getUsername()))
        		.thenReturn(Optional.of(UserMapper.toEntity(updatedDataDTO, null, null, null, null, null, null)));
        UserDTO updatedUser = UserMapper.toDTO(userRepository.findByUsername(createdUser.getUsername()).get());
        
        //Basic
        assertEquals(newUsername, 						updatedUser.getUsername());
        assertEquals(newNickname, 						updatedUser.getNickname());
        assertEquals(newEmail, 							updatedUser.getEmail());
        assertTrue(PasswordHasher.matches("NewPassword", updatedUser.getPassword()));
        
        assertNotEquals(newUsername, 						createdUser.getUsername());
        assertNotEquals(newNickname, 						createdUser.getNickname());
        assertNotEquals(newEmail, 							createdUser.getEmail());
        assertNotEquals(PasswordHasher.hash(newPassword),   createdUser.getPassword());
        
        //User not exist
        assertThrows(NoSuchElementException.class, () -> userService.update(nonExistingUser));
    }
    
    @Test
    @Order(4)
    void userDeleteTest() {
        UserDTO testUser 	= generateTestUser();
        UserDTO testUser2 	= generateTestUser();
        when(userRepository.save(UserMapper.toEntity(testUser, null, null, null, null, null, null)))
		.thenReturn(UserMapper.toEntity(testUser, null, null, null, null, null, null));
        UserDTO createdUser = userService.create(testUser);

        userService.deleteByUsername(createdUser.getUsername());
        
        assertThrows(NoSuchElementException.class, () -> userService.deleteByUsername(testUser2.getUsername()));
    }
    
    @Test
    @Order(5)
    void userFollowTest() {
    	UserDTO test = generateTestUser();
    	UserDTO test2 = generateTestUser();
    	UserDTO test3 = generateTestUser();
    	UserDTO test4 = generateTestUser();
    	when(userRepository.save(UserMapper.toEntity(test, null, null, null, null, null, null)))
				.thenReturn(UserMapper.toEntity(test, null, null, null, null, null, null));
    	UserDTO createdUser 	= userService.create(test);
    	when(userRepository.save(UserMapper.toEntity(test2, null, null, null, null, null, null)))
		.thenReturn(UserMapper.toEntity(test2, null, null, null, null, null, null));
    	UserDTO follower1 		= userService.create(test2);
    	when(userRepository.save(UserMapper.toEntity(test3, null, null, null, null, null, null)))
		.thenReturn(UserMapper.toEntity(test3, null, null, null, null, null, null));
    	UserDTO follower2 		= userService.create(test3);
    	when(userRepository.save(UserMapper.toEntity(test4, null, null, null, null, null, null)))
		.thenReturn(UserMapper.toEntity(test4, null, null, null, null, null, null));
    	UserDTO follower3 		= userService.create(test4);
    	
    	// No followers
    	assertEquals(0, createdUser.getFollowers().size());
    	assertEquals(0, follower1  .getFollowers().size());
    	assertEquals(0, follower1  .getFollowers().size());
    	
    	//After following
    	when(userRepository.findById(follower2.getUserId()))
				.thenReturn(Optional.of(UserMapper.toEntity(follower2, null, null, null, null, null, null)));
    	when(userRepository.findById(follower1.getUserId()))
		.thenReturn(Optional.of(UserMapper.toEntity(follower1, null, null, null, null, null, null)));
    	when(userRepository.findById(createdUser.getUserId()))
				.thenReturn(Optional.of(UserMapper.toEntity(createdUser, null, null, null, null, null, null)));
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
