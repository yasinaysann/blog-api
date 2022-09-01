package com.yasin.blogapi.service;


import com.yasin.blogapi.entity.Comment;
import com.yasin.blogapi.entity.Post;
import com.yasin.blogapi.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post){
        return postRepository.save(post);
    }

    public Post createCommentByPostId(Long id, Comment comment){
        if (comment == null)
             throw new NoSuchElementException("Comment is null");
        Post post = postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Post is Not Found"));
        post.getComments().add(comment);
        return postRepository.save(post);
    }

    public Post getPostById(Long id){
        return postRepository.findById(id).get();
    }


    public List<Post> getAllPost(){
        return  postRepository.findAll();
    }

    @Transactional
    public Post updatePost(Long id,Post post){
        if (post == null) {
            throw new NoSuchElementException("Data is Not Found");
        }
         return postRepository.findById(id).map(post1 -> {
            post1.setTitle(post.getTitle());
            post1.setBody(post.getBody());
            post1.addTag(post.getTags());
            return postRepository.save(post1);
        }).orElseThrow(() -> new IllegalStateException("Update Operation Failed"));
        //return updatedPost;
    }

    public void deletePostById(Long id){
        postRepository.deleteById(id);
    }


}
