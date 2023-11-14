package com.metrica.vibely.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metrica.vibely.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, java.util.UUID>{

}
