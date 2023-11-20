package com.metrica.vibely.data.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.metrica.vibely.data.entity.Post;
import com.metrica.vibely.data.model.dto.PostDTO;
import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.model.enumerator.PostStatus;
import com.metrica.vibely.data.model.enumerator.PostVisibility;

/**
 * <h1>Post DTO Test</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Adri
 */
class PostDTOTest {

	// <<-CONSTANTS->>
    private static final LocalDateTime POST_DATE = LocalDateTime.now();
    private static final PostStatus STATUS = PostStatus.ACTIVE;
    private static final PostVisibility VISIBILITY = PostVisibility.PUBLIC;
    private static final String CONTENT = "content";
    private static final Integer ZERO = 0;
    
    // <<-METHODS->>
    @Test
    @Tag("Constructors")
    void voidConstructorTest() {
        UUID postId = UUID.randomUUID();
        UserDTO owner  = new UserDTO();
        Set<UUID> comments = new HashSet<>();
        Set<UUID> likedBy  = new HashSet<>();
        Set<UUID> savedBy  = new HashSet<>();

        PostDTO post = new PostDTO();
        
        post.setPostId    (postId);
        post.setOwner     (owner);
        post.setPostDate  (POST_DATE);
        post.setStatus    (STATUS);
        post.setVisibility(VISIBILITY);
        post.setContent   (CONTENT);
        post.setLikes     (ZERO);
        post.setTimesSaved(ZERO);
        post.setComments  (comments);
        post.setLikedBy   (likedBy);
        post.setSavedBy   (savedBy);

        assertEquals(postId,    post.getPostId());
        assertEquals(owner,     post.getOwner());
        assertEquals(POST_DATE, post.getPostDate());
        assertEquals(STATUS,    post.getStatus());
        assertEquals(VISIBILITY, post.getVisibility());
        assertEquals(CONTENT,   post.getContent());
        assertEquals(ZERO,      post.getLikes());
        assertEquals(ZERO,      post.getTimesSaved());
        assertEquals(comments,  post.getComments());
        assertEquals(likedBy,   post.getLikedBy());
        assertEquals(savedBy,   post.getSavedBy());
    }
    
    @Test
    @Tag("Constructors")
    void fullArgsConstructorTest() {
        UUID postId = UUID.randomUUID();
        UserDTO owner  = new UserDTO();
        Set<UUID> comments = new HashSet<>();
        Set<UUID> likedBy  = new HashSet<>();
        Set<UUID> savedBy  = new HashSet<>();
        
        PostDTO post = new PostDTO(
                postId,
                POST_DATE,
                STATUS,
                VISIBILITY,
                CONTENT,
                ZERO,
                ZERO,
                owner,
                comments,
                likedBy,
                savedBy);

        assertEquals(postId,    post.getPostId());
        assertEquals(owner,     post.getOwner());
        assertEquals(POST_DATE, post.getPostDate());
        assertEquals(STATUS,    post.getStatus());
        assertEquals(VISIBILITY, post.getVisibility());
        assertEquals(CONTENT,   post.getContent());
        assertEquals(ZERO,      post.getLikes());
        assertEquals(ZERO,      post.getTimesSaved());
        assertEquals(comments,  post.getComments());
        assertEquals(likedBy,   post.getLikedBy());
        assertEquals(savedBy,   post.getSavedBy());
    }

    @Test
    @Tag("Default values")
    void notNullableFieldsAndDefaultValuesTest() {
        Post post = new Post();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        post.setPostId    (null);
        post.setPostDate  (null);
        post.setStatus    (null);
        post.setVisibility(null);
        post.setContent   (null);
        post.setLikes     (null);
        post.setTimesSaved(null);

        assertNotNull(post.getPostId());
        assertEquals(now.format(formatter), post.getPostDate().format(formatter));
        assertEquals(PostStatus.ACTIVE, post.getStatus());
        assertEquals(PostVisibility.PUBLIC, post.getVisibility());
        assertEquals("", post.getContent());
        assertEquals(0, post.getLikes());
        assertEquals(0, post.getTimesSaved());
    }

    @Test
    @Tag("Equality")
    void basicEqualityTest() {
        PostDTO post = new PostDTO();
        
        assertEquals   (post, post);
        assertNotEquals(post, null);
        assertNotEquals(post, "");
    }
    
    @Test
    @Tag("Equality")
    void equalityByIdAndHashCodeTest() {
    	PostDTO post1 = new PostDTO();
    	PostDTO post2 = new PostDTO();
        
        // Both have the same ID
        UUID postId = UUID.randomUUID();
        post1.setPostId(postId);
        post2.setPostId(postId);
        
        assertEquals(post1, post2);
        assertEquals(post1.hashCode(), post2.hashCode());
    }
    
    
    @Test
    @Tag("Equality")
    void inequalityByFieldsTest() {
    	PostDTO post1 = new PostDTO();
    	PostDTO post2 = new PostDTO();

        // Not the same ID
        post1.setPostDate(POST_DATE);
        post1.setContent(CONTENT);
        post1.setStatus(STATUS);
        post1.setVisibility(VISIBILITY);

        post2.setPostDate(POST_DATE);
        post2.setContent(CONTENT);
        post2.setStatus(STATUS);
        post2.setVisibility(VISIBILITY);
        
        assertNotEquals(post1, post2);
    }
}
