package com.example.mvcwithbeansfirst.repository;

import com.example.mvcwithbeansfirst.model.Post;
import org.springframework.stereotype.Repository;
import jakarta.annotation.PostConstruct; // для Spring Boot 2.x используй javax.annotation.PostConstruct
import java.util.ArrayList;
import java.util.List;

@Repository // Делает класс бином Spring
public class InMemoryPostRepository implements PostRepository {

    private final List<Post> posts = new ArrayList<>();
    private Long currentId = 1L;

    @PostConstruct // Вызывается Spring'ом СРАЗУ после создания бина
    public void init() {
        posts.add(new Post(currentId++, "Первый пост", "Изучаем Spring MVC"));
        posts.add(new Post(currentId++, "Второй пост", "Разбираемся с бинами"));
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(posts);
    }

    @Override
    public Post save(Post post) {
        if (post.getId() == null) {
            post.setId(currentId++);
            posts.add(post);
        } else {
            posts.removeIf(p -> p.getId().equals(post.getId()));
            posts.add(post);
        }
        return post;
    }

    @Override
    public Post findById(Long id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
