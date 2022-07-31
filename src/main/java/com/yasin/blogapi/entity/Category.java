package com.yasin.blogapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// todo: Solved
@Entity
public class Category implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Category name is Required")
    @Column(nullable = false,unique = true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Post> posts = new ArrayList<>();

    protected Category(){

    }

    public Category(String name){
        this.name = name;
    }

    public Category(Long id, String name){
        this(name);
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
