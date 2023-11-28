package com.metrica.vibely.data.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.model.enumerator.UserStatus;

/**
 * <h1>User DTO Test</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Adri
 */
class UserDTOTest {

	// <<-CONSTANTS->>
    private static final String USERNAME = "jdoe";
    private static final String PASSWORD = "12345";
    private static final String NICKNAME = "John Doe";
    private static final String EMAIL    = "johndoe@email.com";
    private static final String APIKEY   = "randomApikey";
    private static final UserState   STATE   = UserState.ENABLED;
    private static final PrivacyType PRIVACY = PrivacyType.PUBLIC;
    private static final UserStatus  STATUS  = UserStatus.ONLINE;
    private static final Integer LOGINS = 0;
    private static final LocalDateTime LAST_CONN_DATE = LocalDateTime.now();
    private static final LocalDate     BLOCKED_DATE   = LocalDate.now();
    
 // <<-METHODS->>
    @Test
    @Tag("Constructors")
    void voidConstructorTest() {
        UUID userId = UUID.randomUUID();
        Set<UUID> followers = new HashSet<>();
        Set<UUID> following = new HashSet<>();
        Set<UUID> posts     = new HashSet<>();
        Set<UUID> chats     = new HashSet<>();
        Set<UUID> likes     = new HashSet<>();
        Set<UUID> saves     = new HashSet<>();

        
        UserDTO user = new UserDTO();

        user.setUserId      (userId);
        user.setUsername    (USERNAME);
        user.setPassword    (PASSWORD);
        user.setNickname    (NICKNAME);
        user.setEmail       (EMAIL);
        user.setApikey		(APIKEY);
        user.setState       (STATE);
        user.setPrivacy     (PRIVACY);
        user.setStatus      (STATUS);
        user.setLogins      (LOGINS);
        user.setLastConnDate(LAST_CONN_DATE);
        user.setBlockedDate (BLOCKED_DATE);
        user.setFollowers   (followers);
        user.setFollowing   (following);
        user.setPosts       (posts);
        user.setChats       (chats);
        user.setLikes       (likes);
        user.setSaves       (saves);


        assertEquals(userId,         user.getUserId());
        assertEquals(USERNAME,       user.getUsername());
        assertEquals(PASSWORD,       user.getPassword());
        assertEquals(NICKNAME,       user.getNickname());
        assertEquals(EMAIL,          user.getEmail());
        assertEquals(APIKEY,         user.getApikey());
        assertEquals(STATE,          user.getState());
        assertEquals(PRIVACY,        user.getPrivacy());
        assertEquals(STATUS,         user.getStatus());
        assertEquals(LOGINS,         user.getLogins());
        assertEquals(LAST_CONN_DATE, user.getLastConnDate());
        assertEquals(BLOCKED_DATE,   user.getBlockedDate());
        assertEquals(followers,      user.getFollowers());
        assertEquals(following,      user.getFollowing());
        assertEquals(posts,          user.getPosts());
        assertEquals(chats,          user.getChats());
        assertEquals(likes,          user.getLikes());
        assertEquals(saves,          user.getSaves());
       
    }
    
    @Test
    @Tag("Constructors")
    void fullArgsConstructorTest() {
        UUID userId = UUID.randomUUID();
        Set<UUID> followers = new HashSet<>();
        Set<UUID> following = new HashSet<>();
        Set<UUID> posts     = new HashSet<>();
        Set<UUID> chats     = new HashSet<>();
        Set<UUID> likes     = new HashSet<>();
        Set<UUID> saves     = new HashSet<>();
        
        UserDTO user = new UserDTO(
                userId,
                NICKNAME,
                USERNAME,
                PASSWORD,
                EMAIL,
                APIKEY,
                STATE,
                PRIVACY,
                LOGINS,
                STATUS,
                LAST_CONN_DATE,
                BLOCKED_DATE,
                followers,
                following,
                posts, 
                chats,
                likes,
                saves);

        assertEquals(userId,         user.getUserId());
        assertEquals(NICKNAME,       user.getNickname());
        assertEquals(USERNAME,       user.getUsername());
        assertEquals(PASSWORD,       user.getPassword());
        assertEquals(EMAIL,          user.getEmail());
        assertEquals(APIKEY,         user.getApikey());
        assertEquals(STATE,          user.getState());
        assertEquals(PRIVACY,        user.getPrivacy());
        assertEquals(STATUS,         user.getStatus());
        assertEquals(LOGINS,         user.getLogins());
        assertEquals(LAST_CONN_DATE, user.getLastConnDate());
        assertEquals(BLOCKED_DATE,   user.getBlockedDate());
        assertEquals(followers,      user.getFollowers());
        assertEquals(following,      user.getFollowing());
        assertEquals(posts,          user.getPosts());
        assertEquals(chats,          user.getChats());
        assertEquals(likes,          user.getChats());
        assertEquals(saves,          user.getChats());
    }
    

    @Test
    @Tag("Equality")
    void basicEqualityTest() {
        UserDTO user = new UserDTO();
        
        assertEquals   (user, user);
        assertNotEquals(user, null);
        assertNotEquals(user, "");
    }
    
    @Test
    @Tag("Equality")
    void equalityByIdAndHashCodeTest() {
    	UserDTO user1 = new UserDTO();
        UserDTO user2 = new UserDTO();
        UUID uuid = UUID.randomUUID();
        
        // Both the same ID
        user1.setUserId(uuid);
        user1.setUsername(USERNAME);
        user1.setPassword(PASSWORD);
        user1.setNickname(NICKNAME);
        user1.setEmail   (EMAIL);
        
        user2.setUserId(uuid);
        user2.setUsername(USERNAME);
        user2.setPassword(PASSWORD);
        user2.setNickname(NICKNAME);
        user2.setEmail   (EMAIL);
        
        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }   

    
    @Test
    @Tag("Equality")
    void inequalityByFieldsTest() {
        UserDTO user1 = new UserDTO();
        UserDTO user2 = new UserDTO();

        // Not the same ID
        user1.setUserId(UUID.randomUUID());
        user1.setUsername(USERNAME);
        user1.setPassword(PASSWORD);
        user1.setNickname(NICKNAME);
        user1.setEmail   (EMAIL);
        
        user2.setUserId(UUID.randomUUID());
        user2.setUsername(USERNAME);
        user2.setPassword(PASSWORD);
        user2.setNickname(NICKNAME);
        user2.setEmail   (EMAIL);
        
        assertNotEquals(user1, user2);
    }
}
