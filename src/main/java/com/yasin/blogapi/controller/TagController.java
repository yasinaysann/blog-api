package com.yasin.blogapi.controller;

import com.yasin.blogapi.entity.Tag;
import com.yasin.blogapi.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/tag")
public class TagController {

    private final TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @PostMapping
    public ResponseEntity<Tag> createTag(@Valid @RequestBody Tag tag){
        Tag tag1 = tagRepository.save(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body(tag1);
    }

    @GetMapping
    public List<Tag> getAllTags(){
        return tagRepository.findAll();
    }

    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable Long id){
        return tagRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Data is Not Found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTagById(@PathVariable Long id){
        if (tagRepository.existsById(id)){
            tagRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data is Not Found");
    }
}
