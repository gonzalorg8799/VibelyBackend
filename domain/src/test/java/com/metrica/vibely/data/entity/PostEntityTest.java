package com.metrica.vibely.data.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.metrica.vibely.data.model.enumerator.PostStatus;
import com.metrica.vibely.data.model.enumerator.PostVisibility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * <h1>Post Entity Test</h1>
 * 
 * @since 2023-11-16
 * @version 1.0
 * @author Alex
 */
public class PostEntityTest {

    // <<-FIELD->>
    private Post post;
    
    // <<-METHODS->>
    @BeforeEach
    void setUp() {
        post = new Post();
    }
    
    @Test
    void testGettersAndSetters() {
        UUID postId = UUID.randomUUID();
        User owner  = new User();
        LocalDateTime  postDate   = LocalDateTime.now();
        PostStatus     status     = PostStatus.ACTIVE;
        PostVisibility visibility = PostVisibility.PUBLIC;
        String  content = "";
        Integer likes   = 0;
        Integer timesSaved   = 0;
        Set<Post> comments = new HashSet<>();
        Set<User> likedBy  = new HashSet<>();
        Set<User> savedBy  = new HashSet<>();
        
        post.setPostId    (postId);
        post.setOwner     (owner);
        post.setPostDate  (postDate);
        post.setStatus    (status);
        post.setVisibility(visibility);
        post.setContent   (content);
        post.setLikes     (likes);
        post.setTimesSaved(timesSaved);
        post.setComments  (comments);
        post.setLikedBy   (likedBy);
        post.setSavedBy   (savedBy);

        assertEquals(postId,     post.getPostId());
        assertEquals(owner,      post.getOwner());
        assertEquals(postDate,   post.getPostDate());
        assertEquals(status,     post.getStatus());
        assertEquals(visibility, post.getVisibility());
        assertEquals(content,    post.getContent());
        assertEquals(likes,      post.getLikes());
        assertEquals(timesSaved,      post.getTimesSaved());
        assertEquals(comments,   post.getComments());
        assertEquals(likedBy,    post.getLikedBy());
        assertEquals(savedBy,    post.getSavedBy());
        
        Post testPost = new Post(
                postId,
                postDate,
                status,
                visibility,
                content,
                likes,
                timesSaved,
                owner,
                comments,
                likedBy,
                savedBy);
        
        assertEquals(post, testPost);
    }
    
    @Test
    void testNotNullableFields() {
        post.setPostId    (null);
        post.setPostDate  (null);
        post.setStatus    (null);
        post.setVisibility(null);
        post.setContent   (null);
        post.setLikes     (null);
        post.setTimesSaved(null);

        assertNotNull(post.getPostId());
        assertNotNull(post.getPostDate());
        assertNotNull(post.getStatus());
        assertNotNull(post.getVisibility());
        assertNotNull(post.getContent());
        assertNotNull(post.getLikes());
        assertNotNull(post.getTimesSaved());
    }
    
    @Test
    void testDefaultValues() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        
        post.setPostDate  (null);
        post.setStatus    (null);
        post.setVisibility(null);
        post.setContent   (null);
        post.setLikes     (null);
        post.setTimesSaved(null);
        
        assertEquals(now.format(formatter), post.getPostDate().format(formatter));
        assertEquals(PostStatus.ACTIVE, post.getStatus());
        assertEquals(PostVisibility.PUBLIC, post.getVisibility());
        assertEquals("", post.getContent());
        assertEquals(0, post.getLikes());
        assertEquals(0, post.getTimesSaved());
    }
    
    @Test
    void testPostIdEquality() {
        UUID postId = UUID.randomUUID();
        post.setPostId(postId);
        
        Post testPost = new Post();
        testPost.setPostId(postId);
        
        assertEquals(testPost, post);
    }
    
    @Test
    void testDifferentPostEquality() {
        LocalDateTime now = LocalDateTime.now();
        String content = "Same content";
        
        post.setPostDate(now);
        post.setContent(content);
        post.setStatus(PostStatus.ACTIVE);
        post.setVisibility(PostVisibility.PUBLIC);
        
        Post testPost = new Post();
        testPost.setPostDate(now);
        testPost.setContent(content);
        testPost.setStatus(PostStatus.ACTIVE);
        testPost.setVisibility(PostVisibility.PUBLIC);
        
        assertNotEquals(testPost, post);
    }
    
    @Test
    void testBasicEquality() {
        assertEquals(post, post);
        assertNotEquals(post, null);
        assertNotEquals(post, "");
    }
    
}
