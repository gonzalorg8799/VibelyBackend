package com.metrica.vibely.data.service.impl;

import com.metrica.vibely.data.entity.Admin;
import com.metrica.vibely.data.model.dto.AdminDTO;
import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.model.enumerator.UserStatus;
import com.metrica.vibely.data.model.mapper.AdminMapper;
import com.metrica.vibely.data.repository.AdminRepository;
import com.metrica.vibely.data.service.AdminService;
import com.metrica.vibely.data.util.PasswordHasher;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1>Administrator Service Implementation</h1>
 * 
 * @since 2023-11-27
 * @version 1.0
 * @author Alex
 */
@Service
public class AdminServiceImpl implements AdminService {

    // <<-FIELD->>
    private AdminRepository adminRepository;
    
    // <<-CONSTRUCTOR->>
    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    
    // <<-METHODS->>
    @Override
    public AdminDTO getById(UUID id) {
        Admin admin = this.adminRepository.findById(id).orElseThrow();
        
        if (admin.getState() != UserState.DISABLED)
            return AdminMapper.toDTO(admin);
        throw new NoSuchElementException();
    }

    @Override
    public AdminDTO getByUsername(String username) {
        Admin admin = this.adminRepository.findByUsername(username).orElseThrow();
        return AdminMapper.toDTO(admin);
    }

    @Override
    public AdminDTO getByEmail(String email) {
        Admin admin = this.adminRepository.findByEmail(email).orElseThrow();
        return AdminMapper.toDTO(admin);
    }

    @Override
    public AdminDTO create(AdminDTO adminDTO) {
        Admin admin = AdminMapper.toEntity(adminDTO, null, null, null, null, null, null);
        
        admin.setState  (UserState.ENABLED);
        admin.setPrivacy(PrivacyType.PUBLIC);
        admin.setLogins (0);
        admin.setStatus (UserStatus.OFFLINE);
        admin.setLastConnDate(LocalDateTime.now());
        
        return AdminMapper.toDTO(adminRepository.save(admin));
    }

    @Override
    public AdminDTO update(AdminDTO adminDTO) {
        Admin admin = this.adminRepository.findById(adminDTO.getUserId()).orElseThrow();
        
        String username = adminDTO.getUsername();
        String password = adminDTO.getPassword();
        String nickname = adminDTO.getNickname();
        String email    = adminDTO.getEmail();
        
        if (username != null) admin.setUsername(username);
        if (password != null) admin.setPassword(PasswordHasher.hash(password));
        if (nickname != null) admin.setNickname(nickname);
        if (email    != null) admin.setEmail   (email);
        
        return AdminMapper.toDTO(adminRepository.save(admin));
    }

    @Override
    public void deleteById(UUID id) {
        Admin admin = this.adminRepository.findById(id).orElseThrow();
        admin.setState(UserState.DISABLED);
        this.adminRepository.save(admin);
    }

    @Override
    public void deleteByUsername(String username) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public AdminDTO followUser(UUID adminId, UUID followedUserId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AdminDTO unfollowUser(UUID adminId, UUID followedUserId) {
        // TODO Auto-generated method stub
        return null;
    }

}