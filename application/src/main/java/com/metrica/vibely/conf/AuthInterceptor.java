package com.metrica.vibely.conf;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.metrica.vibely.data.service.UserService;
import com.metrica.vibely.data.util.ApiKeyManager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
	
	private UserService userService;
	private String savedApiKey;
    
	@Autowired
	public AuthInterceptor(UserService userService) {
		this.userService= userService;
	}
    // <<-METHODS->>
	private boolean getStatus(String apiKey, HttpServletResponse response, String savedApiKey) {
		return switch(ApiKeyManager.isValid(apiKey, savedApiKey)) {
			case 0->true;
			case 1->{
	        	response.setStatus(400);
	        	yield false;}
	        case 2->{
	        	response.setStatus(401);
	        	yield false;}
	        default-> false;
		};
	}
	public void getSavedApikey(String apiKey) {
		UUID id=ApiKeyManager.getId(apiKey);
		this.savedApiKey=userService.getById(id).getApikey();
	}
    @Override
    public boolean preHandle(
            @NonNull
            HttpServletRequest request,
            @NonNull
            HttpServletResponse response,
            @NonNull
            Object handler
    ) {
        String apiKey = request.getHeader("x-api-key");
        getSavedApikey(apiKey);
        return getStatus(apiKey, response, savedApiKey);
    }

    @Override
    public void postHandle(
            @NonNull
            HttpServletRequest request,
            @NonNull
            HttpServletResponse response,
            @NonNull
            Object handler,
            ModelAndView mAV
    ) {
        System.err.println("Estamos en el interceptor otra vez");
    }

}