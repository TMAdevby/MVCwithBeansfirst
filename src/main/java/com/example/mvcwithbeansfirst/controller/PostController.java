package com.example.mvcwithbeansfirst.controller;

import com.example.mvcwithbeansfirst.model.Post;
import com.example.mvcwithbeansfirst.service.PostService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAll() {
        return postService.getAllPosts();
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        return postService.createPost(post);
    }
}
