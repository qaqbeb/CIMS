package com.example.demo.config;

import com.example.demo.Interceptor.MainInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MainConfig implements WebMvcConfigurer {
   /* @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MainInterceptor()).
                addPathPatterns("/**").
                excludePathPatterns("/login","/register","/register1","/api/**","/css/**","/images/**","/js/**","/lib/**");
    }*/
}
