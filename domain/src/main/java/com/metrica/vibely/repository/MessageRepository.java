package com.metrica.vibely.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metrica.vibely.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, java.util.UUID>{

}
