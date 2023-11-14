package com.metrica.vibely.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metrica.vibely.data.entity.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, java.util.UUID> {

}
