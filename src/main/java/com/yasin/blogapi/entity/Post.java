package com.yasin.blogapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Post{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String body;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;


    @ManyToMany(fetch = FetchType.LAZY)
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Tag> tag = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<Comment> comments = new HashSet<>();

    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt = Instant.now();



    protected Post(){

    }

    public Post(Long id, String title, String body, Category category, List<Tag> tags, Comment comment) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.category = category;
        this.tag = tags;
        this.comments.add(comment);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Category getCategory() {
        return category;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }
}
