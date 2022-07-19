package com.example.posts.Service;

import com.example.posts.Entity.Post;
import com.example.posts.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public void createPost(Post post) {
        Optional<Post> postOptional = postRepository
                .findPostByTitle(post.getTitle());

        if (postOptional.isPresent()) {
            throw new IllegalStateException("That title already exists.");
        }

        postRepository.save(post);
    }

    @Transactional
    public void updatePost(Long id, Post updatedPost) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalStateException("The task with id " + id + "does not exist."));

        if (updatedPost.getTitle() != null && updatedPost.getTitle().length() > 0 && !Objects.equals(post.getTitle(), updatedPost.getTitle())) {

            post.setTitle(updatedPost.getTitle());
        }

        if (updatedPost.getBody() != null && updatedPost.getBody().length() > 0 && !Objects.equals(post.getBody(), updatedPost.getBody())) {
            post.setBody(updatedPost.getBody());
        }
    }
}
