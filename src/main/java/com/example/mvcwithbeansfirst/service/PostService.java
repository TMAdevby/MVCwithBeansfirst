package com.example.mvcwithbeansfirst.service;

import com.example.mvcwithbeansfirst.model.Post;
import com.example.mvcwithbeansfirst.repository.PostRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    // Конструкторная инъекция — Spring сам найдёт бин PostRepository и передаст его сюда
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post createPost(Post post) {
        if (post.getTitle() == null || post.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Заголовок не может быть пустым");
        }
        return postRepository.save(post);
    }
}
