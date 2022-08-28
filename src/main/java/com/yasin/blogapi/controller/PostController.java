package com.yasin.blogapi.controller;

import com.yasin.blogapi.entity.Comment;
import com.yasin.blogapi.entity.Post;
import com.yasin.blogapi.repository.CategoryRepository;
import com.yasin.blogapi.repository.PostRepository;
import com.yasin.blogapi.repository.TagRepository;
import com.yasin.blogapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {



    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        Post createdPost= postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @GetMapping
    public List<Post> getAllPost(){
        return postService.getAllPost();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }


    @PostMapping("/{id}/comment")
    public ResponseEntity<Post> createCommentByPostId(@PathVariable Long id, @RequestBody Comment comment){

        Post createCommentByPost = postService.createCommentByPostId(id, comment);

        return ResponseEntity.status(HttpStatus.CREATED).body(createCommentByPost);
    }

    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable Long id){
        postService.deletePostById(id);
    }
}
