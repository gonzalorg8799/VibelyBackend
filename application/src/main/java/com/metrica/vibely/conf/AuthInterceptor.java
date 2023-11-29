package com.metrica.vibely.conf;

import com.metrica.vibely.data.util.ApiKeyManager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
    
    // <<-METHODS->>
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

        ApiKeyManager.getExpirationTime(apiKey);

        System.err.println("Estamos en el interceptor");
        
        
        
        return false;
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