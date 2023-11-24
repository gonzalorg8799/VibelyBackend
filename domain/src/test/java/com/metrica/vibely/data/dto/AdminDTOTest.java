package com.metrica.vibely.data.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.metrica.vibely.data.entity.Admin;
import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.Post;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.AdminDTO;
import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.model.enumerator.UserStatus;

/**
 * <h1>Admin DTO Test</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Adri
 */
class AdminDTOTest {

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
        Set<UUID> followers = new HashSet<>();
        Set<UUID> following = new HashSet<>();
        Set<UUID> posts     = new HashSet<>();
        Set<UUID> chats     = new HashSet<>();
        Set<UUID> likes     = new HashSet<>();
        Set<UUID> saves     = new HashSet<>();
        
        AdminDTO admin = new AdminDTO();

        admin.setUserId      (adminId);
        admin.setNickname    (NICKNAME);
        admin.setUsername    (USERNAME);
        admin.setPassword    (PASSWORD);
        admin.setEmail       (EMAIL);
        admin.setApikey      (APIKEY);
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
        admin.setLikes       (likes);
        admin.setSaves       (saves);

        assertEquals(adminId,        admin.getUserId());
        assertEquals(NICKNAME,       admin.getNickname());
        assertEquals(USERNAME,       admin.getUsername());
        assertEquals(PASSWORD,       admin.getPassword());
        assertEquals(EMAIL,          admin.getEmail());
        assertEquals(APIKEY,         admin.getApikey());
        assertEquals(STATE,          admin.getState());
        assertEquals(PRIVACY,        admin.getPrivacy());
        assertEquals(STATUS,         admin.getStatus());
        assertEquals(LOGINS,         admin.getLogins());
        assertEquals(LAST_CONN_DATE, admin.getLastConnDate());
        assertEquals(BLOCKED_DATE,   admin.getBlockedDate());
        assertEquals(followers,      admin.getFollowers());
        assertEquals(following,      admin.getFollowing());
        assertEquals(posts,          admin.getPosts());
        assertEquals(chats,          admin.getChats());
        assertEquals(likes,          admin.getLikes());
        assertEquals(saves,          admin.getSaves());
    }
    
    @Test
    @Tag("Constructors")
    void fullArgsConstructorTest() {
        UUID adminId = UUID.randomUUID();
        Set<UUID> followers = new HashSet<>();
        Set<UUID> following = new HashSet<>();
        Set<UUID> posts     = new HashSet<>();
        Set<UUID> chats     = new HashSet<>();
        Set<UUID> likes     = new HashSet<>();
        Set<UUID> saves     = new HashSet<>();
        
        AdminDTO user = new AdminDTO(
                adminId,
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

        assertEquals(adminId,        user.getUserId());
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

	
}
