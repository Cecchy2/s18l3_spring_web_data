package dariocecchinato.s18l3_spring_web_data.controllers;

import dariocecchinato.s18l3_spring_web_data.entities.BlogPost;
import dariocecchinato.s18l3_spring_web_data.entities.PayloadBodyBlogPost;
import dariocecchinato.s18l3_spring_web_data.services.BlogPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostsController {
    @Autowired
    private BlogPostsService blogPostsService;

    @GetMapping
    public Page<BlogPost> getAllBlogs(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String sortBy){
        return this.blogPostsService.findAllBlogs(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost creaBlog(@RequestBody PayloadBodyBlogPost body){
        return blogPostsService.saveBlogPost(body);
    }

    @GetMapping("/{blogPostId}")
    public BlogPost getBlogById(@PathVariable UUID blogPostId){
        return this.blogPostsService.findById(blogPostId);
    }


/*
    @PutMapping("/{blogPostId}")
    public BlogPost getBlogByIdAndUpdate(@PathVariable UUID blogPostId, @RequestBody BlogPost body){}

    @DeleteMapping("/{blogPostId}")
    public void getBlogByIdAndDelete(@PathVariable UUID blogPostId){}*/
}
