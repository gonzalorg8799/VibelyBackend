package com.metrica.vibely.conf;

import com.metrica.vibely.interceptor.AdminAuthInterceptor;
import com.metrica.vibely.interceptor.AuthInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <h1></h1>
 * 
 * @since 2023-11-29
 * @version 1.0
 * @author Alex, Gonzalo
 */
@Configuration
public class AppConfiguration implements WebMvcConfigurer {

    // <<-FIELD->>
    private AuthInterceptor authInterceptor;
    private AdminAuthInterceptor adminAuthInterceptor;

    // <<-CONSTRUCTOR->>
    @Autowired
    public AppConfiguration(AuthInterceptor authInterceptor, AdminAuthInterceptor adminAuthInterceptor) {
        this.authInterceptor = authInterceptor;
        this.adminAuthInterceptor = adminAuthInterceptor;
    }

    // <<-METHOD->>
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.authInterceptor)
            .addPathPatterns("/api/v1/**")
            .excludePathPatterns("/api/v1/auth/username")
            .excludePathPatterns("/api/v1/auth/email")
            .excludePathPatterns("/api/v1/users/signup")
            .excludePathPatterns("/api/v1/admin/users/signup");
        registry.addInterceptor(this.adminAuthInterceptor)
        	.addPathPatterns("/api/v1/**")
        	.excludePathPatterns("/api/v1/auth/admin/username")
        	.excludePathPatterns("/api/v1/auth/admin/email")
        	.excludePathPatterns("/api/v1/users/signup")
        	.excludePathPatterns("/api/v1/admin/users/signup");
    }

}