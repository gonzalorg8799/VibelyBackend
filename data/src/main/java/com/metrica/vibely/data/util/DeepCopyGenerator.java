package com.metrica.vibely.data.util;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h1>Deep Copy Generator</h1>
 * 
 * @since 2023-11-17
 * @version 1.0
 * @author Alex
 */
public class DeepCopyGenerator {

    // <<-CONSTRUCTOR->>
    private DeepCopyGenerator() {
    }
    
    // <<-METHOD->>
    public static <T extends Copyable<T>> Set<T> generateCopy(Set<T> collection) {
        return collection.stream()
                .map(T::deepCopy)
                .collect(Collectors.toSet());
    }
    
}
