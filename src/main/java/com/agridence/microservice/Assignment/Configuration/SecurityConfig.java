package com.agridence.microservice.Assignment.Configuration;

import com.agridence.microservice.Assignment.Utility.AuthenticationUtility;
import com.agridence.microservice.Assignment.Utility.JWTUtility;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationUtility())
                .addPathPatterns("/notes/**","/users/**")
                .excludePathPatterns("/users/auth/register","/users/auth/login");
    }
}
