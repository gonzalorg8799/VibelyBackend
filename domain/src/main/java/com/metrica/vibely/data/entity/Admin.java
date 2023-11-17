package com.metrica.vibely.data.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ForeignKey;

/**
 * <h1>Administrator Entity</h1>
 * 
 * @since 2023-11-13
 * @version 1.0
 * @author Adrian, Alex
 */
public class Admin {
	
    // <<-FIELDS->>
    
    // Relations
	@OneToOne(optional = false)
	@JoinColumn(
	        name = "user_id",
	        unique = true,
	        nullable = false,
	        foreignKey = @ForeignKey(name = "fk_admin_user"))
	private User user;
    
}
