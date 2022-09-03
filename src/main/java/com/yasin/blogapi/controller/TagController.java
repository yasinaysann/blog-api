package com.yasin.blogapi.controller;

import com.yasin.blogapi.entity.Tag;
import com.yasin.blogapi.repository.TagRepository;
import com.yasin.blogapi.service.TagService;
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

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseEntity<Tag> createTag(@Valid @RequestBody Tag tag){
        Tag savedTag = tagService.createTag(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTag);
    }

    @GetMapping
    public List<Tag> getAllTags(){
        return tagService.getAllTags();
    }

    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable Long id){
        return tagService.getTagById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTagById(@PathVariable Long id){
        if (!tagService.deleteTagById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data is Not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
