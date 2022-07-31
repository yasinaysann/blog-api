package com.yasin.blogapi.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

// todo: solved
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Tag Name is Required")
    @Column(nullable = false,unique = true)
    private String name;
    //fetch = FetchType.EAGER,cascade = CascadeType.MERGE
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Post> post = new ArrayList<>();

    protected Tag(){

    }

    public Tag(Long id, String name){
        this.id = id;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }
}
