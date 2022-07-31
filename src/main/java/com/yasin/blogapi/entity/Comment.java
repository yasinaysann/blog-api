package com.yasin.blogapi.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;

@Entity

public class Comment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Commenter name is Required")
    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false,unique = true)
    private String email;

    @NotBlank(message = "Comment is Required")
    @Column(nullable = false)
    private String comment;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    protected Comment(){

    }

    public Comment(Long id, String name, String email, String comment) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getComment() {
        return comment;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
