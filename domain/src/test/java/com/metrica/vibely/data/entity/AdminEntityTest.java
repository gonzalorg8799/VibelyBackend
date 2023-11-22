package com.metrica.vibely.data.entity;

import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.model.enumerator.UserStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Admin Entity Test</h1>
 * 
 * @since 2023-11-18
 * @version 1.0
 * @author Alex
 */
public class AdminEntityTest {

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
        UUID adminId = UUID.randomUUID();
        Set<User> followers = new HashSet<>();
        Set<User> following = new HashSet<>();
        Set<Post> posts     = new HashSet<>();
        Set<Chat> chats     = new HashSet<>();
        
        Admin admin = new Admin();

        admin.setUserId      (adminId);
        admin.setUsername    (USERNAME);
        admin.setPassword    (PASSWORD);
        admin.setNickname    (NICKNAME);
        admin.setEmail       (EMAIL);
        admin.setState       (STATE);
        admin.setPrivacy     (PRIVACY);
        admin.setStatus      (STATUS);
        admin.setLogins      (LOGINS);
        admin.setLastConnDate(LAST_CONN_DATE);
        admin.setBlockedDate (BLOCKED_DATE);
        admin.setFollowers   (followers);
        admin.setFollowing   (following);
        admin.setPosts       (posts);
        admin.setChats       (chats);

        assertEquals(adminId,       admin.getUserId());
        assertEquals(USERNAME,     admin.getUsername());
        assertEquals(PASSWORD,     admin.getPassword());
        assertEquals(NICKNAME,     admin.getNickname());
        assertEquals(EMAIL,        admin.getEmail());
        assertEquals(STATE,        admin.getState());
        assertEquals(PRIVACY,      admin.getPrivacy());
        assertEquals(STATUS,       admin.getStatus());
        assertEquals(LOGINS,       admin.getLogins());
        assertEquals(LAST_CONN_DATE, admin.getLastConnDate());
        assertEquals(BLOCKED_DATE,  admin.getBlockedDate());
        assertEquals(followers,    admin.getFollowers());
        assertEquals(following,    admin.getFollowing());
        assertEquals(posts,        admin.getPosts());
        assertEquals(chats,        admin.getChats());
    }
    
    @Test
    @Tag("Constructors")
    void fullArgsConstructorTest() {
        UUID adminId = UUID.randomUUID();
        Set<User> followers = new HashSet<>();
        Set<User> following = new HashSet<>();
        Set<Post> posts     = new HashSet<>();
        Set<Chat> chats     = new HashSet<>();
        
        Admin user = new Admin(
                adminId,
                USERNAME,
                PASSWORD,
                NICKNAME,
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
                chats);

        assertEquals(adminId,        user.getUserId());
        assertEquals(USERNAME,       user.getUsername());
        assertEquals(PASSWORD,       user.getPassword());
        assertEquals(NICKNAME,       user.getNickname());
        assertEquals(EMAIL,          user.getEmail());
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
    }

}
