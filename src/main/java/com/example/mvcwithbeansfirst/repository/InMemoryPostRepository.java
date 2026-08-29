package com.example.mvcwithbeansfirst.repository;

import com.example.mvcwithbeansfirst.model.Post;
import java.util.ArrayList;
import java.util.List;

// ВАЖНО: здесь НЕТ аннотации @Repository!
// Этот класс станет бином через @Bean в AppConfig
public class InMemoryPostRepository implements PostRepository {

    private final List<Post> posts = new ArrayList<>();
    private Long currentId = 1L;

    public InMemoryPostRepository() {
        // Инициализация прямо в конструкторе (альтернатива @PostConstruct)
        posts.add(new Post(currentId++, "Первый пост", "Бины через @Bean"));
        posts.add(new Post(currentId++, "Второй пост", "Конфигурация Spring"));
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(posts);
    }

    @Override
    public Post save(Post post) {
        if (post.getId() == null) {
            post.setId(currentId++);
        }
        posts.removeIf(p -> p.getId().equals(post.getId()));
        posts.add(post);
        return post;
    }
}
