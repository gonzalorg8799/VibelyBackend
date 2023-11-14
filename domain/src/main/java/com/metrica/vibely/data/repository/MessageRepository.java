package com.metrica.vibely.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metrica.vibely.data.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, java.util.UUID>{

}
