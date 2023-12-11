package com.metrica.vibely.data.util;

/**
 * <h1>Copyable</h1>
 * 
 * @since 2023-11-17
 * @version 1.0
 * @author Alex
 */
@FunctionalInterface
public interface Copyable<Entity> {

    /**
     * Performs a deep copy of the given Entity
     * 
     * @return a deep copy of the given entity
     */
    Entity deepCopy();
    
}
