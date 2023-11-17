package com.metrica.vibely.data.util;

import java.util.Set;
import java.util.stream.Collectors;

public class DeepCopyGenerator {
    
    public static <T extends CloneableEntity<T>> Set<T> generateCopy(Set<T> collection) {
        return collection.stream()
                .map(T::clone)
                .collect(Collectors.toSet());
    }
    
}

interface CloneableEntity<T> {
    
    T clone();
    
}
