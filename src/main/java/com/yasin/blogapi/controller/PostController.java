package com.yasin.blogapi.controller;

import com.yasin.blogapi.entity.Comment;
import com.yasin.blogapi.entity.Post;
import com.yasin.blogapi.repository.CategoryRepository;
import com.yasin.blogapi.repository.PostRepository;
import com.yasin.blogapi.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final TagRepository tagRepository;

    @Autowired
    private final CategoryRepository categoryRepository;


    public PostController(PostRepository postRepository, TagRepository tagRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
        this.categoryRepository = categoryRepository;
    }


    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        Post post1= postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post1);
    }

    @GetMapping
    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id){
        return ResponseEntity.ok(postRepository.findById(id).get());
    }


    @PostMapping("/{id}/comment")
    public ResponseEntity<Post> createCommentByPostId(@PathVariable Long id, @RequestBody Comment comment){
        Optional<Post> postOptional = postRepository.findById(id);
        postOptional.get().getComments().add(comment);

        Post post = postRepository.save(postOptional.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
}
