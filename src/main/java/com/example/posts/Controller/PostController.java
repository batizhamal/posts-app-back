package com.example.posts.Controller;

import com.example.posts.Entity.Post;
import com.example.posts.Repository.PostRepository;
import com.example.posts.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8081/")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public List<Post> getPosts(@RequestParam(required = false) String title) {
        return postService.getPosts(title);
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> getPostById(@PathVariable("id") Long id) {
        return postService.getPostById(id);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
    }

    @PostMapping("/posts")
    public void createPost(@RequestBody Post post) {
        postService.createPost(post);
    }

    @PutMapping(path="/posts/{id}")
    public void updatePost( @PathVariable("id") Long id,
                            @RequestBody(required = false) Post post) {
        postService.updatePost(id, post);
    }

}
