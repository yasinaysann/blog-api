package com.yasin.blogapi;

import com.yasin.blogapi.entity.Category;
import com.yasin.blogapi.entity.Comment;
import com.yasin.blogapi.entity.Post;
import com.yasin.blogapi.entity.Tag;
import com.yasin.blogapi.repository.CategoryRepository;
import com.yasin.blogapi.repository.CommentRepository;
import com.yasin.blogapi.repository.PostRepository;
import com.yasin.blogapi.repository.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
public class BlogApiApplication{
    public static void main(String[] args) {
        SpringApplication.run(BlogApiApplication.class, args);
    }
}
