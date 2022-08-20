package com.yasin.blogapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.*;

// todo: solved
@Entity
public class Tag implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Tag Name is Required")
    @Column(nullable = false,unique = true)
    private String name;
    //fetch = FetchType.EAGER,cascade = CascadeType.MERGE
    @ManyToMany(mappedBy = "tags")
    private Set<Post> posts = new HashSet<>();

    protected Tag(){

    }

    public Tag(Long id, String name, Set<Post> posts){
        this.id = id;
        this.name = name;
        this.posts = posts;
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

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }


}
