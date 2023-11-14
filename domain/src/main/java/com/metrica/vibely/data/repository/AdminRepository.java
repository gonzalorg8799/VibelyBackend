package com.metrica.vibely.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metrica.vibely.data.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, java.util.UUID> {

}
