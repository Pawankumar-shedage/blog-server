package com.blogPostApp.blogserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CrossOriginsConfig implements WebMvcConfigurer {
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        // You can configure your CORS policy here.
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")  // Replace with your allowed origin
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Specify the HTTP methods you want to allow
                .allowedHeaders("Content-Type")  // Specify the allowed request headers
                .allowCredentials(true);  // Enable credentials (if needed)
    }
}
