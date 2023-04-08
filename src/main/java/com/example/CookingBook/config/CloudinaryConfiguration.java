package com.example.CookingBook.config;

import com.cloudinary.Cloudinary;
import com.example.CookingBook.config.mock.MockCloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class CloudinaryConfiguration {

    //TODO:Трябва да се коментира от тук заради тестовете
    @Value("${cloudinary.cloud-name}")
    private String cloudApiName;
    @Value("${cloudinary.api-key}")
    private String cloudApiKey;

    @Value("${cloudinary.api-secret}")
    private String cloudApiSecret;

    @Bean
    @ConditionalOnExpression("${cloud.service.mocked} == false")
    public Cloudinary cloudinary() {
        return new Cloudinary(new HashMap<String, Object>(){{
            put("cloud_name", cloudApiName);
            put("api_key", cloudApiKey);
            put("api_secret", cloudApiSecret);
        }});
    }
    //TODO:Трябва да се коментира до тук заради тестовете

    @Bean
    @ConditionalOnExpression("${cloud.service.mocked} == true")
    public Cloudinary mockCloudinary () {

        System.out.println("---");

        return new MockCloudinary();
    }

}
