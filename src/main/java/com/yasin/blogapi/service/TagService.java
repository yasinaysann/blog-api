package com.yasin.blogapi.service;

import com.yasin.blogapi.entity.Post;
import com.yasin.blogapi.entity.Tag;
import com.yasin.blogapi.repository.PostRepository;
import com.yasin.blogapi.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final PostRepository postRepository;

    public TagService(TagRepository tagRepository, PostRepository postRepository) {
        this.tagRepository = tagRepository;
        this.postRepository = postRepository;
    }

    public Tag createTag(Tag tag){
        return tagRepository.save(tag);
    }

    public Tag getTagById(Long id){
        return tagRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Data Not Found"));
    }

    public List<Tag> getAllTags(){
        return tagRepository.findAll();
    }

    public Boolean deleteTagById(Long id){
        if (!tagRepository.existsById(id)){
            return false;
        }else {
            tagRepository.deleteById(id);
            return true;
        }
    }

}
