package com.metrica.vibely.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @since 2023-11-20
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1")
public class AdminController {
	
//	<<--FIELDS-->>
	private AdminService adminService;
	
//	<<--CONSTRUCTOR-->>
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

//	<<--METHODS-->>
	

}
