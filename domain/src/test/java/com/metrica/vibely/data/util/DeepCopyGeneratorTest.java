package com.metrica.vibely.data.util;

import org.junit.jupiter.api.Test;

import com.metrica.vibely.data.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

/**
 * <h1>Deep Copy Generator Tests</h1>
 * 
 * @since 2023-11-29
 * @version 1.0
 * @author Alex
 */
public class DeepCopyGeneratorTest {

    @Test
    void ss() {
        Set<User> users = Set.of(
                new User(),
                new User());
        
        assertEquals(users, DeepCopyGenerator.generateCopy(users));
    }
    
    
}
