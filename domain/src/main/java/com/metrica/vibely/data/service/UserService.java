package com.metrica.vibely.data.service;

import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.UserDTO;

public interface UserService  {
	
	User getByUsername(String username);	
	User create(UserDTO user); // subject to change
	User update(UserDTO user); // 
	void deleteByUsername(String username);
	
}
