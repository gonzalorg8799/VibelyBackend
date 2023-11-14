package com.metrica.vibely.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metrica.vibely.model.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, java.util.UUID> {

}
