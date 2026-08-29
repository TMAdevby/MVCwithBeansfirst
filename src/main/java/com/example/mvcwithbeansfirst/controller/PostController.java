package com.example.mvcwithbeansfirst.controller;

import com.example.mvcwithbeansfirst.model.Post;
import com.example.mvcwithbeansfirst.service.PostService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final RestTemplate restTemplate; // Бин из @Bean в AppConfig!

    public PostController(PostService postService, RestTemplate restTemplate) {
        this.postService = postService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public List<Post> getAll() {
        return postService.getAllPosts();
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        return postService.createPost(post);
    }

    // Новый эндпоинт — показывает, какой профиль сейчас активен
    @GetMapping("/profile")
    public String getProfile() {
        return postService.getCurrentProfile();
    }

    // Эндпоинт, демонстрирующий работу RestTemplate (бина из @Bean)
    // Он делает HTTP-запрос на внешний ресурс
    @GetMapping("/external")
    public String getExternalData() {
        // RestTemplate создан через @Bean в AppConfig
        return restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/1", String.class);
    }
}
