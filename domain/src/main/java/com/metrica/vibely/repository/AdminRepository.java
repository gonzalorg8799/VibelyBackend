package com.metrica.vibely.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metrica.vibely.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, java.util.UUID> {

}
