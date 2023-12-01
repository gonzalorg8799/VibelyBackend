package com.metrica.vibely.interceptor;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.metrica.vibely.data.service.AuthService;
import com.metrica.vibely.data.util.ApiKeyManager;

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
        return switch (ApiKeyManager.isValid(apiKey)) {
            case 0 -> {
                UUID userId = ApiKeyManager.getId(apiKey);
                System.err.println(userId);
                
                String userApiKey = this.authService.getApikey(userId);
                yield userApiKey.equals(apiKey);
            }
            case 1 -> {
                response.setStatus(400);
                yield false;
            }
            case 2 -> {
                response.setStatus(401);
                yield false;
            }
            default -> false;
        };
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
        System.err.println("Estamos en el interceptor otra vez");
    }

}