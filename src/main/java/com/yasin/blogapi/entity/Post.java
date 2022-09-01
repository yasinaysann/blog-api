package com.yasin.blogapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Post implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String body;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_post_id")
    @JsonBackReference(value = "post-category")
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE)
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonBackReference(value = "post-tag")
    private Set<Tag> tags;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<Comment> comments = new HashSet<>();

    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt = Instant.now();



    protected Post(){

    }

    public Post(Long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;

    }
    public void addTag(Tag tag){
        this.tags.add(tag);
        tag.getPosts().add(this);
    }
    public void addTag(Set<Tag> tags){
        tags.forEach(tag -> addTag(tag));
    }

    public void removeTag(Long tagId){
        Tag tag = this.tags.stream().filter(tag1 -> tag1.getId() == tagId).findFirst().orElse(null);
        if (tag != null){
            this.tags.remove(tag);
            tag.getPosts().remove(this);
        }
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


    public Set<Tag> getTags() {
        return tags;
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

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(title, post.title) && Objects.equals(body, post.body) && Objects.equals(category, post.category) && Objects.equals(tags, post.tags) && Objects.equals(comments, post.comments) && Objects.equals(createdAt, post.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, body, category, tags, comments, createdAt);
    }
}
