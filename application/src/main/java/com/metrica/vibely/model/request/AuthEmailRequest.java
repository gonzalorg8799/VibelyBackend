package com.metrica.vibely.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AuthEmailRequest {
	//	<--ATRIBUTES-->
	@NotNull
	@NotBlank
	private String email;
	@NotNull
	@NotBlank
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@$!%*#?&])[A-Za-z0-9@$!%*#?&]{12,}$")
	private String password;
	
	// <--CONSTRUCTOR-->
	public AuthEmailRequest() {
	}

	// <--GETTERS & SETTERS-->
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
}
