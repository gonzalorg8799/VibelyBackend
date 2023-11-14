package com.metrica.vibely.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metrica.vibely.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, java.util.UUID> {
	
}
