package com.example.mvcwithbeansfirst.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiClient {

    private final RestTemplate restTemplate;

    // Берем наш @Bean из AppConfig
    public ExternalApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Конкретный метод для конкретной задачи
    public String getExternalPostById(Long id) {
        // 1. Формируем конкретный URL с использованием переменной (аналог @PathVariable)
        String url = "https://jsonplaceholder.typicode.com/posts/" + id;

        // 2. Делаем запрос. RestTemplate сам подставит id вместо {id}, если использовать такой синтаксис:
        // String url = "https://jsonplaceholder.typicode.com/posts/{id}";
        // return restTemplate.getForObject(url, String.class, id);

        return restTemplate.getForObject(url, String.class);
    }

    // Завтра ты можешь добавить сюда другой метод для другого сайта:
    // public String getWeather(String city) { ... }
}
