package com.example.mvcwithbeansfirst.config;

import com.example.mvcwithbeansfirst.profile.DevProfile;
import com.example.mvcwithbeansfirst.profile.ProdProfile;
import com.example.mvcwithbeansfirst.profile.SystemProfile;
import com.example.mvcwithbeansfirst.repository.InMemoryPostRepository;
import com.example.mvcwithbeansfirst.repository.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration  // Говорит Spring: "В этом классе есть методы, создающие бины"
public class AppConfig {

    // ==========================================
    // СЛУЧАЙ №1: Бин стороннего класса
    // ==========================================
    // RestTemplate — класс из библиотеки Spring, мы не можем поставить на него @Component.
    // Поэтому создаём его через @Bean. Это САМЫЙ ЧАСТЫЙ случай в реальных проектах.
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // ==========================================
    // СЛУЧАЙ №2: Кастомная настройка бина
    // ==========================================
    // ObjectMapper можно создать и автоматически, но мы хотим особые настройки.
    // Через @Bean мы контролируем, КАК именно создаётся объект.
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // Красивый JSON с отступами
        return mapper;
    }

    // ==========================================
    // СЛУЧАЙ №3: Условное создание бина
    // ==========================================
    // В зависимости от свойства в application.properties создаётся либо Dev, либо Prod
    @Bean
    @ConditionalOnProperty(
            name = "app.profile.dev",
            havingValue = "true"
    )
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(
            name = "app.profile.dev",
            havingValue = "false",
            matchIfMissing = true  // Если свойства нет — по умолчанию production
    )
    public SystemProfile prodProfile() {
        return new ProdProfile();
    }

    // ==========================================
    // СЛУЧАЙ №4: Бин вместо @Component
    // ==========================================
    // Мы могли бы поставить @Repository на InMemoryPostRepository,
    // но создаём его через @Bean. Это даёт контроль над созданием.
    @Bean
    public PostRepository postRepository() {
        return new InMemoryPostRepository();
    }
}