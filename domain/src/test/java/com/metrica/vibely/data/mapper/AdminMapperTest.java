package com.metrica.vibely.data.mapper;

import org.junit.jupiter.api.Test;

import com.metrica.vibely.data.entity.Admin;
import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.Post;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.AdminDTO;
import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.model.enumerator.UserStatus;
import com.metrica.vibely.data.model.mapper.AdminMapper;
import com.metrica.vibely.data.model.mapper.UserMapper;

import org.junit.jupiter.api.Tag;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @since 2023-11-22
 * @author Adrian
 * @version 1.0
 *
 */
class AdminMapperTest {

    // <<-CONSTANTS->>
    private static final UUID ADMIN_ID = UUID.randomUUID();
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "adminPass";
    private static final String ADMIN_NICKNAME = "Admin Nick";
    private static final String ADMIN_EMAIL = "admin@example.com";
    private static final String ADMIN_APIKEY = "adminApikey";
    private static final UserState ADMIN_STATE = UserState.ENABLED;
    private static final PrivacyType ADMIN_PRIVACY = PrivacyType.PRIVATE;
    private static final UserStatus ADMIN_STATUS = UserStatus.ONLINE;
    private static final Integer ADMIN_LOGINS = 1;
    private static final LocalDateTime ADMIN_LAST_CONN_DATE = LocalDateTime.now();
    private static final LocalDate ADMIN_BLOCKED_DATE = LocalDate.now();

    // <<-METHODS->>
    private AdminDTO createAdminDTO() {
        AdminDTO adminDTO = new AdminDTO();
        
        adminDTO.setUserId(ADMIN_ID);
        adminDTO.setUsername(ADMIN_USERNAME);
        adminDTO.setPassword(ADMIN_PASSWORD);
        adminDTO.setNickname(ADMIN_NICKNAME);
        adminDTO.setEmail(ADMIN_EMAIL);
        adminDTO.setApikey(ADMIN_APIKEY);
        adminDTO.setState(ADMIN_STATE);
        adminDTO.setPrivacy(ADMIN_PRIVACY);
        adminDTO.setStatus(ADMIN_STATUS);
        adminDTO.setLogins(ADMIN_LOGINS);
        adminDTO.setLastConnDate(ADMIN_LAST_CONN_DATE);
        adminDTO.setBlockedDate(ADMIN_BLOCKED_DATE);
        
        return adminDTO;
    }
    
    private Admin createAdmin() {
    	Admin admin = new Admin();
        
        admin.setUserId(ADMIN_ID);
        admin.setUsername(ADMIN_USERNAME);
        admin.setPassword(ADMIN_PASSWORD);
        admin.setNickname(ADMIN_NICKNAME);
        admin.setEmail(ADMIN_EMAIL);
        admin.setApikey(ADMIN_APIKEY);
        admin.setState(ADMIN_STATE);
        admin.setPrivacy(ADMIN_PRIVACY);
        admin.setStatus(ADMIN_STATUS);
        admin.setLogins(ADMIN_LOGINS);
        admin.setLastConnDate(ADMIN_LAST_CONN_DATE);
        admin.setBlockedDate(ADMIN_BLOCKED_DATE);
        
        return admin;
    }

    @Test
    @Tag("toEntity")
    void toEntityTest() {
        AdminDTO adminDTO = createAdminDTO();
        
        Set<User> followers = new HashSet<>();
        Set<User> following = new HashSet<>();
        Set<Post> posts = new HashSet<>();
        Set<Chat> chats = new HashSet<>();

        Admin admin = AdminMapper.toEntity(adminDTO, followers, following, posts, chats);

        assertEquals(adminDTO.getUserId(), admin.getUserId());
        assertEquals(adminDTO.getUsername(), admin.getUsername());
        assertEquals(adminDTO.getPassword(), admin.getPassword());
        assertEquals(adminDTO.getNickname(), admin.getNickname());
        assertEquals(adminDTO.getEmail(), admin.getEmail());
        assertEquals(adminDTO.getApikey(), admin.getApikey());
        assertEquals(adminDTO.getState(), admin.getState());
        assertEquals(adminDTO.getPrivacy(), admin.getPrivacy());
        assertEquals(adminDTO.getStatus(), admin.getStatus());
        assertEquals(adminDTO.getLogins(), admin.getLogins());
        assertEquals(adminDTO.getLastConnDate(), admin.getLastConnDate());
        assertEquals(adminDTO.getBlockedDate(), admin.getBlockedDate());
        
        assertEquals(followers, admin.getFollowers());
        assertEquals(following, admin.getFollowing());
        assertEquals(posts, admin.getPosts());
        assertEquals(chats, admin.getChats());
        
    }

    @Test
    @Tag("toDTO")
    void toDTOTest() {
        Admin admin = createAdmin();
        
        Set<User> followers = new HashSet<>();
        Set<User> following = new HashSet<>();
        Set<Post> posts = new HashSet<>();
        Set<Chat> chats = new HashSet<>();

        admin.setFollowers(followers);
        admin.setFollowing(following);
        admin.setPosts(posts);
        admin.setChats(chats);
        		
        AdminDTO adminDTO = AdminMapper.toDTO(admin);

        assertEquals(admin.getUserId(), adminDTO.getUserId());
        assertEquals(admin.getUsername(), adminDTO.getUsername());
        assertEquals(admin.getPassword(), adminDTO.getPassword());
        assertEquals(admin.getNickname(), adminDTO.getNickname());
        assertEquals(admin.getEmail(), adminDTO.getEmail());
        assertEquals(admin.getApikey(), adminDTO.getApikey());
        assertEquals(admin.getState(), adminDTO.getState());
        assertEquals(admin.getPrivacy(), adminDTO.getPrivacy());
        assertEquals(admin.getStatus(), adminDTO.getStatus());
        assertEquals(admin.getLogins(), adminDTO.getLogins());
        assertEquals(admin.getLastConnDate(), adminDTO.getLastConnDate());
        assertEquals(admin.getBlockedDate(), adminDTO.getBlockedDate());
        
        assertEquals(followers.stream()
        		.map(User::getUserId)
        		.collect(Collectors.toSet()), 
        		adminDTO.getFollowers());
        assertEquals(following.stream()
        		.map(User::getUserId)
        		.collect(Collectors.toSet()),
        		adminDTO.getFollowing());
        assertEquals(posts.stream()
        		.map(Post::getPostId)
        		.collect(Collectors.toSet()),
        		adminDTO.getPosts());
        assertEquals(chats.stream()
        		.map(Chat::getChatId)
        		.collect(Collectors.toSet()),
        		adminDTO.getChats());
 
        
    }
}

