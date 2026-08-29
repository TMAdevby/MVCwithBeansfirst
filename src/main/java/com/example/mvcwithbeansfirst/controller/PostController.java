package com.example.mvcwithbeansfirst.controller;

import com.example.mvcwithbeansfirst.model.Post;
import com.example.mvcwithbeansfirst.service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; // Импорт есть, всё верно!
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final RestTemplate restTemplate;

    // 1. ДОБАВЛЕНО: Объявляем поле для ObjectMapper
    private final ObjectMapper objectMapper;

    // 2. ДОБАВЛЕНО: Добавляем ObjectMapper в параметры конструктора
    public PostController(PostService postService, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.postService = postService;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper; // 3. ДОБАВЛЕНО: Присваиваем значение полю
    }

    @GetMapping
    public List<Post> getAll() {
        return postService.getAllPosts();
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @GetMapping("/profile")
    public String getProfile() {
        return postService.getCurrentProfile();
    }

    @GetMapping("/external")
    public String getExternalData() {
        return restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/1", String.class);
    }

    @GetMapping("/external-data")
    public String getExternalData1() {
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        String externalResponse = restTemplate.getForObject(url, String.class);
        return "Данные с чужого сервера:\n" + externalResponse;
    }

    @GetMapping("/convert-to-json")
    public String convertToManualJson() throws JsonProcessingException {
        Post myPost = new Post(99L, "Тестовый пост", "Проверка ObjectMapper");

        // Теперь здесь всё будет работать, потому что переменная objectMapper объявлена выше
        String jsonResult = objectMapper.writeValueAsString(myPost);

        return "Вот как наш кастомный ObjectMapper видит Java-объект:\n\n" + jsonResult;
    }
}