package com.metrica.vibely.data.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ForeignKey;

public class Admin{
	
	@OneToOne(optional = false)
	@JoinColumn(name = "user_id", unique = true, nullable = false, foreignKey = @ForeignKey(name = "fk_admin_user"))
	private User user;

    
}
