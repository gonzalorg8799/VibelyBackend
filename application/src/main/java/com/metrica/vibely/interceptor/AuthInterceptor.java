package com.metrica.vibely.interceptor;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.metrica.vibely.data.model.enumerator.HttpStatusEnum;
import com.metrica.vibely.data.util.ApiKeyManager;
import com.metrica.vibely.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * <h1>Authentication Interceptor</h1>
 * 
 * @since 2023-11-29
 * @version 1.0
 * @author Alex, Gonzalo
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
	
    // <<-FIELD->>
	private AuthService authService;

    // <<-CONSTRUCTOR->>
	@Autowired
	public AuthInterceptor(AuthService authService) {
		this.authService = authService;
	}
	
    // <<-METHODS->>
    private boolean setStatus(HttpServletResponse response, String apiKey) {
    	if(ApiKeyManager.isValid(apiKey) == HttpStatusEnum.OK) {
    		UUID userId = ApiKeyManager.getId(apiKey);
    		return this.authService.getApikey(userId).equals(apiKey);
    	}
    	if(ApiKeyManager.isValid(apiKey) == HttpStatusEnum.BAD_REQUEST) { 
    		response.setStatus(HttpStatusEnum.BAD_REQUEST.getStatus()); 
    	}
    	if(ApiKeyManager.isValid(apiKey) == HttpStatusEnum.INVALID_CREDENTIALS) {
    		response.setStatus(HttpStatusEnum.INVALID_CREDENTIALS.getStatus());
    	}
    	return false;
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
        return setStatus(response, apiKey);
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

    }

}