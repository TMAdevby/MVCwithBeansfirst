package com.example.mvcwithbeansfirst.service;

import com.example.mvcwithbeansfirst.model.Post;
import com.example.mvcwithbeansfirst.profile.SystemProfile;
import com.example.mvcwithbeansfirst.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final SystemProfile systemProfile; // Бин из @Bean в AppConfig!

    // Spring автоматически найдёт бины PostRepository и SystemProfile
    // (созданные через @Bean в AppConfig) и передаст их сюда
    public PostService(PostRepository postRepository, SystemProfile systemProfile) {
        this.postRepository = postRepository;
        this.systemProfile = systemProfile;
    }

    public List<Post> getAllPosts() {
        // Используем бин SystemProfile, созданный через @Bean
        System.out.println(">>> " + systemProfile.getProfile());
        return postRepository.findAll();
    }

    public Post createPost(Post post) {
        if (post.getTitle() == null || post.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Заголовок не может быть пустым");
        }
        return postRepository.save(post);
    }

    // Метод, который показывает, что бин SystemProfile реально работает
    public String getCurrentProfile() {
        return systemProfile.getProfile();
    }
}
