package com.metrica.vibely.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 */
@Configuration
public class AppConfiguration implements WebMvcConfigurer {

    // <<-FIELD->>
    private AuthInterceptor authInterceptor;

    // <<-CONSTRUCTOR->>
    @Autowired
    public AppConfiguration(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    // <<-METHOD->>
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.authInterceptor)
            .addPathPatterns("/api/v1/**")
            .excludePathPatterns("/api/v1/auth")
            .excludePathPatterns("/api/v1/users/signup")
            .excludePathPatterns("/api/v1/admin/users/signup");
    }
    
}
