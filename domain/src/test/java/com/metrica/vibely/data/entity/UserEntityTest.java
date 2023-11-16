package com.metrica.vibely.data.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.model.enumerator.UserStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * <h1>User Entity Test</h1>
 * 
 * @since 2023-11-15
 * @version 1.0
 * @author Alex
 */
public class UserEntityTest {

    // <<-FIELD->>
    private User user;

    // <<-METHODS->>
    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testGettersAndSetters() {
        UUID   userId   = UUID.randomUUID();
        String username = "jdoe";
        String password = "12345";
        String nickname = "John Doe";
        String email    = "johndoe@email.com";
        UserState   state   = UserState.ENABLED;
        PrivacyType privacy = PrivacyType.PUBLIC;
        UserStatus  status  = UserStatus.ONLINE;
        Integer     logins  = 0;
        LocalDateTime lastConnDate = LocalDateTime.now();
        LocalDate     blockedDate  = LocalDate.now();
        Set<User> followers = new HashSet<>();
        Set<User> following = new HashSet<>();
        Set<Post> posts     = new HashSet<>();
        Set<Chat> chats     = new HashSet<>();

        user.setUserId      (userId);
        user.setUsername    (username);
        user.setPassword    (password);
        user.setNickname    (nickname);
        user.setEmail       (email);
        user.setState       (state);
        user.setPrivacy     (privacy);
        user.setStatus      (status);
        user.setLogins      (logins);
        user.setLastConnDate(lastConnDate);
        user.setBlockedDate (blockedDate);
        user.setFollowers   (followers);
        user.setFollowing   (following);
        user.setPosts       (posts);
        user.setChats       (chats);

        assertEquals(userId,       user.getUserId());
        assertEquals(username,     user.getUsername());
        assertEquals(password,     user.getPassword());
        assertEquals(nickname,     user.getNickname());
        assertEquals(email,        user.getEmail());
        assertEquals(state,        user.getState());
        assertEquals(privacy,      user.getPrivacy());
        assertEquals(status,       user.getStatus());
        assertEquals(logins,       user.getLogins());
        assertEquals(lastConnDate, user.getLastConnDate());
        assertEquals(blockedDate,  user.getBlockedDate());
        assertEquals(followers,    user.getFollowers());
        assertEquals(following,    user.getFollowing());
        assertEquals(posts,        user.getPosts());
        assertEquals(chats,        user.getChats());
        
        User testUser = new User(
                userId,
                username,
                password,
                nickname,
                email,
                state,
                privacy,
                logins,
                status,
                lastConnDate,
                blockedDate,
                followers,
                following,
                posts,
                chats);
        assertEquals(user, testUser);
    }
    
    @Test
    void testNotNullableFields() {
        user.setUserId      (null);
        user.setLastConnDate(null);
        user.setState       (null);
        user.setPrivacy     (null);
        user.setStatus      (null);
        user.setLogins      (null);

        assertNotNull(user.getUserId());
        assertNotNull(user.getLastConnDate());
        assertNotNull(user.getState());
        assertNotNull(user.getPrivacy());
        assertNotNull(user.getStatus());
        assertNotNull(user.getLogins());
    }
    
    @Test
    void testDefaultValues() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime currentDateTime = LocalDateTime.now();

        user.setLastConnDate(null);
        user.setState       (null);
        user.setPrivacy     (null);
        user.setStatus      (null);
        user.setLogins      (null);
        
        assertEquals(currentDateTime.format(formatter), user.getLastConnDate().format(formatter));
        assertEquals(UserState.ENABLED,  user.getState());
        assertEquals(PrivacyType.PUBLIC, user.getPrivacy());
        assertEquals(UserStatus.OFFLINE, user.getStatus());
        assertEquals(0,                  user.getLogins());
    }
    
    @Test
    void testIdEquality() {
        UUID userId = UUID.randomUUID();
        user.setUserId(userId);
        
        User excpectedUser = new User();
        excpectedUser.setUserId(userId);
        
        assertEquals(excpectedUser, user);
    }
    
    @Test
    void testUserIdNullEquality() {
        String username = "jdoe";
        String password = "12345";
        String nickname = "John Doe";
        String email    = "johndoe@email.com";

        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setEmail   (email);
        
        User excpectedUser = new User();
        excpectedUser.setUsername(username);
        excpectedUser.setPassword(password);
        excpectedUser.setNickname(nickname);
        excpectedUser.setEmail   (email);
        
        assertNotEquals(excpectedUser, user);
    }
    
    @Test
    void testBasicEquality() {
        assertEquals(user, user);
        assertNotEquals(user, null);
        assertNotEquals(user, "");
    }
    
}
