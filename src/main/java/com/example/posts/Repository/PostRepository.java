package com.example.posts.Repository;

import com.example.posts.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findPostByTitle(String title);

    List<Post> findPostByTitleContaining(String title);
}
