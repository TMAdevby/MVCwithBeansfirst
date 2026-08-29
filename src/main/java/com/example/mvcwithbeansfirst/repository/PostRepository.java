package com.example.mvcwithbeansfirst.repository;

import com.example.mvcwithbeansfirst.model.Post;
import java.util.List;

public interface PostRepository {
    List<Post> findAll();
    Post save(Post post);
    Post findById(Long id);
}
